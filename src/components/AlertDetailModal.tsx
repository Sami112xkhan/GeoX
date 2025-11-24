import { motion } from "motion/react";
import { X, ExternalLink, Save, Share2, MapPin, Clock, Layers } from "lucide-react";
import { DisasterData } from "./DisasterCard";
import { Button } from "./ui/button";

interface AlertDetailModalProps {
  disaster: DisasterData | null;
  onClose: () => void;
}

export function AlertDetailModal({ disaster, onClose }: AlertDetailModalProps) {
  if (!disaster) return null;

  const getDisasterIcon = (type: string) => {
    switch (type) {
      case "earthquake":
        return "🔴";
      case "wildfire":
        return "🔥";
      case "volcano":
        return "🌋";
      case "flood":
        return "🌊";
      case "storm":
        return "⛈️";
      default:
        return "⚠️";
    }
  };

  return (
    <motion.div
      className="fixed inset-0 z-50 flex items-end sm:items-center justify-center p-4"
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      exit={{ opacity: 0 }}
      onClick={onClose}
    >
      {/* Backdrop */}
      <motion.div 
        className="absolute inset-0 bg-black/20 backdrop-blur-md"
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        exit={{ opacity: 0 }}
      />

      {/* Modal - Premium bottom sheet style on mobile */}
      <motion.div
        className="relative glass-morphism-3d rounded-t-3xl sm:rounded-3xl p-6 w-full max-h-[85vh] overflow-y-auto premium-shadow"
        initial={{ y: "100%", scale: 0.95 }}
        animate={{ y: 0, scale: 1 }}
        exit={{ y: "100%", scale: 0.95 }}
        transition={{ type: "spring", damping: 30, stiffness: 300 }}
        onClick={(e) => e.stopPropagation()}
      >
        {/* Pull indicator (mobile style) */}
        <div className="flex justify-center -mt-3 mb-4 sm:hidden">
          <div className="w-10 h-1 bg-black/20 rounded-full" />
        </div>

        {/* Close button */}
        <button
          onClick={onClose}
          className="absolute top-4 right-4 p-2 hover:bg-black/5 active:bg-black/10 rounded-full transition-colors z-10"
        >
          <X className="w-6 h-6 text-black" />
        </button>

        {/* Header */}
        <div className="mb-6">
          <div className="flex items-center gap-3 mb-3">
            <span className="text-3xl">{getDisasterIcon(disaster.type)}</span>
            <h2 className="text-black">{disaster.title}</h2>
          </div>
          {disaster.magnitude && (
            <div className="inline-block px-3 py-1.5 bg-red-500/10 border border-red-500/20 rounded-full text-red-600 font-semibold text-sm">
              Magnitude {disaster.magnitude}
            </div>
          )}
          {disaster.category && (
            <div className="inline-block px-3 py-1.5 bg-orange-500/10 border border-orange-500/20 rounded-full text-orange-600 font-semibold text-sm">
              {disaster.category}
            </div>
          )}
        </div>

        {/* Details */}
        <div className="space-y-4 mb-6">
          <div className="flex items-start gap-3">
            <div className="w-9 h-9 bg-[#C4FF0D]/10 rounded-xl flex items-center justify-center flex-shrink-0">
              <MapPin className="w-4 h-4 text-[#C4FF0D]" />
            </div>
            <div>
              <p className="text-black/50 text-sm font-medium">Location</p>
              <p className="text-black font-semibold">{disaster.location}</p>
              <p className="text-black/40 text-sm mt-1 font-mono">
                {disaster.coordinates[0].toFixed(4)}°, {disaster.coordinates[1].toFixed(4)}°
              </p>
            </div>
          </div>

          <div className="flex items-start gap-3">
            <div className="w-9 h-9 bg-[#C4FF0D]/10 rounded-xl flex items-center justify-center flex-shrink-0">
              <Clock className="w-4 h-4 text-[#C4FF0D]" />
            </div>
            <div>
              <p className="text-black/50 text-sm font-medium">Time</p>
              <p className="text-black font-semibold">{disaster.time}</p>
            </div>
          </div>

          {disaster.depth && (
            <div className="flex items-start gap-3">
              <div className="w-9 h-9 bg-[#C4FF0D]/10 rounded-xl flex items-center justify-center flex-shrink-0">
                <Layers className="w-4 h-4 text-[#C4FF0D]" />
              </div>
              <div>
                <p className="text-black/50 text-sm font-medium">Depth</p>
                <p className="text-black font-semibold">{disaster.depth}</p>
              </div>
            </div>
          )}

          {disaster.description && (
            <div className="liquid-glass-subtle rounded-2xl p-4">
              <p className="text-black/70 text-sm leading-relaxed">{disaster.description}</p>
            </div>
          )}
        </div>

        {/* Map preview */}
        <div className="liquid-glass-subtle rounded-2xl h-32 mb-6 overflow-hidden relative">
          <div className="absolute inset-0 bg-gradient-to-br from-gray-200/50 to-gray-100/50" />
          <div className="absolute inset-0 flex items-center justify-center">
            <motion.div 
              className="w-4 h-4 bg-red-500 rounded-full shadow-lg"
              animate={{ scale: [1, 1.2, 1] }}
              transition={{ duration: 2, repeat: Infinity }}
            />
          </div>
        </div>

        {/* Action buttons - Premium touch-friendly size */}
        <div className="grid grid-cols-3 gap-3 mb-6">
          <Button
            variant="outline"
            className="liquid-glass-subtle border-black/5 text-black hover:bg-black/5 active:bg-black/10 rounded-2xl py-6 flex flex-col gap-1.5 font-medium"
          >
            <ExternalLink className="w-5 h-5" />
            <span className="text-xs">Source</span>
          </Button>
          <Button
            variant="outline"
            className="liquid-glass-subtle border-black/5 text-black hover:bg-black/5 active:bg-black/10 rounded-2xl py-6 flex flex-col gap-1.5 font-medium"
          >
            <Save className="w-5 h-5" />
            <span className="text-xs">Save</span>
          </Button>
          <Button
            variant="outline"
            className="liquid-glass-subtle border-black/5 text-black hover:bg-black/5 active:bg-black/10 rounded-2xl py-6 flex flex-col gap-1.5 font-medium"
          >
            <Share2 className="w-5 h-5" />
            <span className="text-xs">Share</span>
          </Button>
        </div>

        {/* AI Prediction */}
        <div className="liquid-glass-subtle rounded-2xl p-5 border-2 border-[#C4FF0D]/20 lime-glow">
          <h4 className="text-black mb-2 font-semibold">AI Prediction</h4>
          <p className="text-black/60 text-sm mb-3">Next 7-day risk assessment</p>
          <div className="flex items-center justify-between">
            <span className="text-4xl font-bold bg-gradient-to-r from-[#C4FF0D] to-[#9FCC0A] bg-clip-text text-transparent">78%</span>
            <span className="text-black/60 text-sm font-medium">Moderate Confidence</span>
          </div>
        </div>
      </motion.div>
    </motion.div>
  );
}
