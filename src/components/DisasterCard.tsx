import { motion } from "motion/react";
import { Flame, AlertCircle, CloudRain, Mountain } from "lucide-react";

export interface DisasterData {
  id: string;
  type: "earthquake" | "wildfire" | "volcano" | "flood" | "storm";
  title: string;
  magnitude?: number;
  category?: string;
  location: string;
  time: string;
  coordinates: [number, number];
  description?: string;
  depth?: string;
}

interface DisasterCardProps {
  disaster: DisasterData;
  onClick: () => void;
}

const disasterIcons = {
  earthquake: AlertCircle,
  wildfire: Flame,
  volcano: Mountain,
  flood: CloudRain,
  storm: CloudRain,
};

const disasterColors = {
  earthquake: "bg-red-500",
  wildfire: "bg-orange-500",
  volcano: "bg-purple-500",
  flood: "bg-blue-500",
  storm: "bg-cyan-500",
};

const disasterAccentColors = {
  earthquake: "text-red-600",
  wildfire: "text-orange-600",
  volcano: "text-purple-600",
  flood: "text-blue-600",
  storm: "text-cyan-600",
};

export function DisasterCard({ disaster, onClick }: DisasterCardProps) {
  const Icon = disasterIcons[disaster.type];
  const colorClass = disasterColors[disaster.type];
  const accentColor = disasterAccentColors[disaster.type];

  return (
    <motion.div
      whileTap={{ scale: 0.96 }}
      onClick={onClick}
      className="liquid-glass rounded-3xl p-4 min-w-[280px] cursor-pointer hover:bg-white/80 active:bg-white/90 transition-all premium-shadow"
    >
      <div className="flex items-start gap-3">
        <div className={`${colorClass} p-3 rounded-2xl flex-shrink-0 shadow-md`}>
          <Icon className="w-6 h-6 text-white" />
        </div>
        <div className="flex-1 min-w-0">
          <h4 className="text-black mb-1 truncate font-semibold">{disaster.title}</h4>
          <p className="text-black/50 text-sm mb-2 truncate">{disaster.location}</p>
          <div className="flex items-center justify-between">
            <span className={`${accentColor} font-semibold text-sm`}>
              {disaster.magnitude ? `M ${disaster.magnitude}` : disaster.category}
            </span>
            <span className="text-black/40 text-xs font-medium">{disaster.time}</span>
          </div>
        </div>
      </div>
    </motion.div>
  );
}
