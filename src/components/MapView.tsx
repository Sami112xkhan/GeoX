import { motion } from "motion/react";
import { DisasterData } from "./DisasterCard";

interface MapViewProps {
  disasters: DisasterData[];
  onPinClick: (disaster: DisasterData) => void;
}

const pinColors = {
  earthquake: "#ef4444",
  wildfire: "#f97316",
  volcano: "#a855f7",
  flood: "#3b82f6",
  storm: "#06b6d4",
};

export function MapView({ disasters, onPinClick }: MapViewProps) {
  return (
    <div className="w-full h-full relative overflow-hidden">
      {/* Gradient background */}
      <div className="absolute inset-0 bg-gradient-to-br from-green-50/80 via-blue-50/80 to-purple-50/80" />
      
      {/* Subtle grid pattern */}
      <div className="absolute inset-0 opacity-10">
        <svg width="100%" height="100%" xmlns="http://www.w3.org/2000/svg">
          <defs>
            <pattern id="premium-grid" width="40" height="40" patternUnits="userSpaceOnUse">
              <path
                d="M 40 0 L 0 0 0 40"
                fill="none"
                stroke="rgba(0,0,0,0.15)"
                strokeWidth="0.5"
              />
            </pattern>
          </defs>
          <rect width="100%" height="100%" fill="url(#premium-grid)" />
        </svg>
      </div>

      {/* Continents outline (simplified, more elegant) */}
      <div className="absolute inset-0 flex items-center justify-center opacity-5">
        <div className="w-[80%] h-[60%] relative">
          {/* Simple world representation */}
          <div className="absolute top-[30%] left-[20%] w-32 h-24 border-2 border-black/40 rounded-full" />
          <div className="absolute top-[35%] left-[45%] w-40 h-32 border-2 border-black/40 rounded-lg" />
          <div className="absolute top-[50%] left-[15%] w-28 h-20 border-2 border-black/40 rounded-full" />
        </div>
      </div>

      {/* Ambient glow areas */}
      <div className="absolute top-10 left-10 w-64 h-64 bg-[#C4FF0D]/15 rounded-full blur-3xl" />
      <div className="absolute bottom-20 right-10 w-72 h-72 bg-blue-400/10 rounded-full blur-3xl" />
      <div className="absolute top-1/2 left-1/2 w-80 h-80 bg-purple-400/10 rounded-full blur-3xl -translate-x-1/2 -translate-y-1/2" />

      {/* Disaster pins */}
      {disasters.map((disaster, index) => {
        // Distribute pins across the map (mock positioning)
        const left = 20 + (index * 15) % 60;
        const top = 25 + (index * 20) % 50;

        return (
          <motion.div
            key={disaster.id}
            className="absolute cursor-pointer"
            style={{
              left: `${left}%`,
              top: `${top}%`,
            }}
            initial={{ scale: 0, opacity: 0 }}
            animate={{ scale: 1, opacity: 1 }}
            transition={{ delay: index * 0.1, duration: 0.5, type: "spring" }}
            whileHover={{ scale: 1.3 }}
            whileTap={{ scale: 0.9 }}
            onClick={() => onPinClick(disaster)}
          >
            {/* Pin with premium glass effect */}
            <div className="relative">
              {/* Outer pulse ring */}
              <motion.div
                className="absolute inset-0 w-4 h-4 rounded-full -translate-x-1/2 -translate-y-1/2"
                style={{ 
                  backgroundColor: pinColors[disaster.type],
                  opacity: 0.3
                }}
                animate={{
                  scale: [1, 2, 1],
                  opacity: [0.3, 0, 0.3],
                }}
                transition={{
                  duration: 2,
                  repeat: Infinity,
                  ease: "easeInOut"
                }}
              />
              
              {/* Main pin */}
              <motion.div
                className="w-4 h-4 rounded-full relative"
                style={{ 
                  backgroundColor: pinColors[disaster.type],
                  boxShadow: `
                    0 0 20px ${pinColors[disaster.type]}80,
                    0 2px 8px rgba(0, 0, 0, 0.2),
                    inset 0 1px 2px rgba(255, 255, 255, 0.3)
                  `
                }}
              >
                {/* Highlight dot */}
                <div
                  className="absolute top-1 left-1 w-1.5 h-1.5 rounded-full bg-white/60"
                />
              </motion.div>
            </div>
          </motion.div>
        );
      })}

      {/* Gradient overlay for depth */}
      <div className="absolute inset-0 bg-gradient-to-t from-white/10 to-transparent pointer-events-none" />
    </div>
  );
}
