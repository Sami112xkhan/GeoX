import { useState } from "react";
import { motion } from "motion/react";
import { X, Flame, Mountain, CloudRain, Zap } from "lucide-react";
import { Slider } from "./ui/slider";
import { Button } from "./ui/button";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "./ui/select";

interface FiltersScreenProps {
  onClose: () => void;
  onApply: (filters: FilterState) => void;
}

export interface FilterState {
  minMagnitude: number;
  radius: number;
  types: {
    volcano: boolean;
    wildfire: boolean;
    flood: boolean;
    storm: boolean;
    earthquake: boolean;
  };
}

export function FiltersScreen({ onClose, onApply }: FiltersScreenProps) {
  const [minMagnitude, setMinMagnitude] = useState([2]);
  const [radius, setRadius] = useState("100");
  const [types, setTypes] = useState({
    volcano: true,
    wildfire: true,
    flood: true,
    storm: true,
    earthquake: true,
  });

  const handleApply = () => {
    onApply({
      minMagnitude: minMagnitude[0],
      radius: parseInt(radius),
      types,
    });
    onClose();
  };

  const toggleType = (type: keyof typeof types) => {
    setTypes((prev) => ({ ...prev, [type]: !prev[type] }));
  };

  return (
    <motion.div
      className="fixed inset-0 z-50 flex items-end"
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      exit={{ opacity: 0 }}
      onClick={onClose}
    >
      {/* Backdrop */}
      <div className="absolute inset-0 bg-black/20 backdrop-blur-md" />

      {/* Bottom sheet */}
      <motion.div
        className="relative glass-morphism-3d rounded-t-3xl p-6 w-full max-h-[80vh] overflow-y-auto premium-shadow"
        initial={{ y: "100%" }}
        animate={{ y: 0 }}
        exit={{ y: "100%" }}
        transition={{ type: "spring", damping: 25, stiffness: 300 }}
        onClick={(e) => e.stopPropagation()}
      >
        {/* Handle */}
        <div className="flex justify-center mb-4">
          <div className="w-10 h-1 bg-black/20 rounded-full" />
        </div>

        {/* Header */}
        <div className="flex items-center justify-between mb-6">
          <h2 className="text-black">Filters</h2>
          <button
            onClick={onClose}
            className="p-2 hover:bg-black/5 active:bg-black/10 rounded-full transition-colors"
          >
            <X className="w-5 h-5 text-black" />
          </button>
        </div>

        {/* Earthquake filters */}
        <div className="space-y-5 mb-6">
          <div>
            <h4 className="text-black mb-4 font-semibold">Earthquake Settings</h4>

            {/* Magnitude slider */}
            <div className="liquid-glass-subtle rounded-2xl p-4 mb-4">
              <div className="flex items-center justify-between mb-3">
                <span className="text-black/80 font-medium">Minimum Magnitude</span>
                <span className="text-[#C4FF0D] font-bold bg-[#C4FF0D]/10 px-2 py-1 rounded-lg">{minMagnitude[0].toFixed(1)}</span>
              </div>
              <Slider
                value={minMagnitude}
                onValueChange={setMinMagnitude}
                min={0}
                max={8}
                step={0.1}
                className="w-full [&_[role=slider]]:bg-[#C4FF0D] [&_[role=slider]]:border-white [&_[role=slider]]:shadow-md"
              />
              <div className="flex justify-between mt-2 text-xs text-black/40 font-medium">
                <span>0</span>
                <span>8</span>
              </div>
            </div>

            {/* Radius selector */}
            <div className="liquid-glass-subtle rounded-2xl p-4">
              <label className="text-black/80 block mb-3 font-medium">Search Radius</label>
              <Select value={radius} onValueChange={setRadius}>
                <SelectTrigger className="liquid-glass border-black/5 text-black">
                  <SelectValue />
                </SelectTrigger>
                <SelectContent className="liquid-glass border-black/5">
                  <SelectItem value="50">50 km</SelectItem>
                  <SelectItem value="100">100 km</SelectItem>
                  <SelectItem value="200">200 km</SelectItem>
                  <SelectItem value="500">500 km</SelectItem>
                  <SelectItem value="1000">1000 km</SelectItem>
                </SelectContent>
              </Select>
            </div>
          </div>

          {/* Disaster types */}
          <div>
            <h4 className="text-black mb-4 font-semibold">Disaster Types</h4>
            <div className="grid grid-cols-2 gap-3">
              <ToggleButton
                icon={Mountain}
                label="Volcano"
                color="bg-purple-500"
                active={types.volcano}
                onClick={() => toggleType("volcano")}
              />
              <ToggleButton
                icon={Flame}
                label="Wildfire"
                color="bg-orange-500"
                active={types.wildfire}
                onClick={() => toggleType("wildfire")}
              />
              <ToggleButton
                icon={CloudRain}
                label="Flood"
                color="bg-blue-500"
                active={types.flood}
                onClick={() => toggleType("flood")}
              />
              <ToggleButton
                icon={Zap}
                label="Storm"
                color="bg-cyan-500"
                active={types.storm}
                onClick={() => toggleType("storm")}
              />
            </div>
          </div>
        </div>

        {/* Apply button - Premium macOS style */}
        <motion.div whileTap={{ scale: 0.98 }}>
          <Button
            onClick={handleApply}
            className="w-full bg-[#C4FF0D] hover:bg-[#C4FF0D]/90 active:bg-[#C4FF0D]/80 text-black rounded-2xl py-7 font-semibold lime-glow-strong premium-shadow"
          >
            Apply Filters
          </Button>
        </motion.div>
      </motion.div>
    </motion.div>
  );
}

interface ToggleButtonProps {
  icon: React.ElementType;
  label: string;
  color: string;
  active: boolean;
  onClick: () => void;
}

function ToggleButton({ icon: Icon, label, color, active, onClick }: ToggleButtonProps) {
  return (
    <motion.button
      onClick={onClick}
      className={`liquid-glass-subtle rounded-2xl p-4 transition-all premium-shadow ${
        active ? "border-2 border-black/10" : "border border-black/5"
      }`}
      whileTap={{ scale: 0.95 }}
    >
      <div className={`${color} w-10 h-10 rounded-xl flex items-center justify-center mb-2 shadow-md ${
        active ? "opacity-100" : "opacity-40"
      }`}>
        <Icon className="w-5 h-5 text-white" />
      </div>
      <p className={`text-sm font-medium ${active ? "text-black" : "text-black/40"}`}>{label}</p>
    </motion.button>
  );
}
