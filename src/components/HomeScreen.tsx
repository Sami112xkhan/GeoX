import { useState } from "react";
import { Bell, Filter, Search } from "lucide-react";
import { motion, AnimatePresence } from "motion/react";
import { DisasterData, DisasterCard } from "./DisasterCard";
import { MapView } from "./MapView";
import { AlertDetailModal } from "./AlertDetailModal";
import { Input } from "./ui/input";

interface HomeScreenProps {
  disasters: DisasterData[];
  onFilterClick: () => void;
}

export function HomeScreen({ disasters, onFilterClick }: HomeScreenProps) {
  const [selectedDisaster, setSelectedDisaster] = useState<DisasterData | null>(null);
  const [searchQuery, setSearchQuery] = useState("");
  const [isExpanded, setIsExpanded] = useState(false);

  const filteredDisasters = disasters.filter(
    (d) =>
      d.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
      d.location.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <div className="h-full flex flex-col relative">
      {/* Top bar - Premium macOS app bar */}
      <div className="liquid-glass border-b border-black/5 px-6 py-4 flex items-center justify-between z-10">
        <h1 className="text-black text-2xl tracking-tight">GeoX</h1>
        <motion.button 
          whileTap={{ scale: 0.92 }}
          className="p-2.5 hover:bg-black/5 active:bg-black/10 rounded-xl transition-colors relative"
        >
          <Bell className="w-5 h-5 text-black/70" />
          <motion.span 
            className="absolute top-2 right-2 w-2 h-2 bg-[#C4FF0D] rounded-full border-2 border-white"
            animate={{ scale: [1, 1.2, 1] }}
            transition={{ duration: 2, repeat: Infinity }}
          />
        </motion.button>
      </div>

      {/* Map */}
      <div className="flex-1 relative">
        <MapView disasters={disasters} onPinClick={setSelectedDisaster} />

        {/* Last updated pill */}
        <motion.div
          className="absolute top-4 left-1/2 -translate-x-1/2 liquid-glass-subtle rounded-full px-4 py-2 premium-shadow"
          initial={{ opacity: 0, y: -10 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.5 }}
        >
          <p className="text-black/70 text-sm font-medium">Updated 5 min ago</p>
        </motion.div>
      </div>

      {/* Bottom sheet - macOS style draggable */}
      <motion.div
        drag="y"
        dragConstraints={{ top: 0, bottom: 0 }}
        dragElastic={0.2}
        onDragEnd={(e, { offset, velocity }) => {
          if (offset.y > 100 || velocity.y > 500) {
            setIsExpanded(false);
          } else if (offset.y < -100 || velocity.y < -500) {
            setIsExpanded(true);
          }
        }}
        animate={{ height: isExpanded ? "70vh" : "45vh" }}
        transition={{ type: "spring", damping: 30, stiffness: 300 }}
        className="glass-morphism-3d border-t border-black/5 rounded-t-3xl p-5 space-y-4 flex flex-col touch-none premium-shadow"
      >
        {/* Handle - macOS style */}
        <div className="flex justify-center -mt-2 mb-2 cursor-grab active:cursor-grabbing">
          <div className="w-10 h-1 bg-black/20 rounded-full" />
        </div>

        {/* Search */}
        <div className="relative flex-shrink-0">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-black/40" />
          <Input
            placeholder="Search disasters..."
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            className="liquid-glass-subtle border-black/5 text-black placeholder:text-black/40 pl-10 rounded-2xl h-11 font-medium"
          />
        </div>

        {/* Scrollable disaster cards */}
        <div className="overflow-x-auto overflow-y-hidden flex-1 -mx-5 px-5">
          <div className="flex gap-3 pb-2">
            {filteredDisasters.map((disaster, index) => (
              <motion.div
                key={disaster.id}
                initial={{ opacity: 0, x: -20 }}
                animate={{ opacity: 1, x: 0 }}
                transition={{ delay: index * 0.05 }}
              >
                <DisasterCard
                  disaster={disaster}
                  onClick={() => setSelectedDisaster(disaster)}
                />
              </motion.div>
            ))}
          </div>
        </div>
      </motion.div>

      {/* FAB - macOS style */}
      <motion.button
        onClick={onFilterClick}
        className="absolute bottom-[47vh] right-5 w-14 h-14 bg-[#C4FF0D] hover:bg-[#C4FF0D]/90 active:bg-[#C4FF0D]/80 rounded-full flex items-center justify-center lime-glow-strong premium-shadow z-20 transition-colors"
        whileHover={{ scale: 1.05 }}
        whileTap={{ scale: 0.92 }}
        initial={{ scale: 0, rotate: -180 }}
        animate={{ scale: 1, rotate: 0 }}
        transition={{ delay: 0.3, type: "spring", stiffness: 200 }}
      >
        <Filter className="w-5 h-5 text-black" />
      </motion.button>

      {/* Alert detail modal */}
      <AnimatePresence>
        {selectedDisaster && (
          <AlertDetailModal
            disaster={selectedDisaster}
            onClose={() => setSelectedDisaster(null)}
          />
        )}
      </AnimatePresence>
    </div>
  );
}
