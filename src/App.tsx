import { useState } from "react";
import { motion, AnimatePresence } from "motion/react";
import { Home, BarChart3, Search, Settings, Info } from "lucide-react";
import { Toaster } from "./components/ui/sonner";
import { SplashScreen } from "./components/SplashScreen";
import { HomeScreen } from "./components/HomeScreen";
import { InsightsScreen } from "./components/InsightsScreen";
import { FiltersScreen, FilterState } from "./components/FiltersScreen";
import { SettingsScreen } from "./components/SettingsScreen";
import { AboutScreen } from "./components/AboutScreen";
import { DisasterData } from "./components/DisasterCard";

// Mock disaster data
const mockDisasters: DisasterData[] = [
  {
    id: "1",
    type: "earthquake",
    title: "Magnitude 6.2 Earthquake",
    magnitude: 6.2,
    location: "Tokyo, Japan",
    time: "2 hours ago",
    coordinates: [35.6762, 139.6503],
    depth: "10 km",
    description: "A moderate earthquake struck near Tokyo, felt across the Kanto region. No major damage reported.",
  },
  {
    id: "2",
    type: "wildfire",
    title: "Forest Fire",
    category: "Large",
    location: "California, USA",
    time: "5 hours ago",
    coordinates: [36.7783, -119.4179],
    description: "Wildfire burning in Northern California, affecting approximately 5,000 acres.",
  },
  {
    id: "3",
    type: "volcano",
    title: "Volcanic Activity",
    category: "Alert Level 3",
    location: "Mount Merapi, Indonesia",
    time: "1 day ago",
    coordinates: [-7.5407, 110.4458],
    description: "Increased volcanic activity detected. Authorities monitoring closely.",
  },
  {
    id: "4",
    type: "earthquake",
    title: "Magnitude 5.8 Earthquake",
    magnitude: 5.8,
    location: "Chile",
    time: "3 hours ago",
    coordinates: [-33.4489, -70.6693],
    depth: "25 km",
    description: "Earthquake detected off the coast of Chile. Minor tremors felt inland.",
  },
  {
    id: "5",
    type: "storm",
    title: "Tropical Storm",
    category: "Category 2",
    location: "Gulf of Mexico",
    time: "6 hours ago",
    coordinates: [25.7617, -80.1918],
    description: "Tropical storm forming in the Gulf, expected to strengthen.",
  },
  {
    id: "6",
    type: "flood",
    title: "Flash Flooding",
    category: "Severe",
    location: "Bangladesh",
    time: "12 hours ago",
    coordinates: [23.685, 90.3563],
    description: "Heavy monsoon rains causing widespread flooding in multiple regions.",
  },
  {
    id: "7",
    type: "earthquake",
    title: "Magnitude 4.5 Earthquake",
    magnitude: 4.5,
    location: "Greece",
    time: "8 hours ago",
    coordinates: [39.0742, 21.8243],
    depth: "15 km",
    description: "Minor earthquake in central Greece. No significant damage.",
  },
];

type Screen = "splash" | "home" | "insights" | "filters" | "settings" | "about";

export default function App() {
  const [currentScreen, setCurrentScreen] = useState<Screen>("splash");
  const [showFilters, setShowFilters] = useState(false);
  const [filters, setFilters] = useState<FilterState>({
    minMagnitude: 2,
    radius: 100,
    types: {
      volcano: true,
      wildfire: true,
      flood: true,
      storm: true,
      earthquake: true,
    },
  });

  const handleGetStarted = () => {
    setCurrentScreen("home");
  };

  const handleApplyFilters = (newFilters: FilterState) => {
    setFilters(newFilters);
  };

  // Filter disasters based on current filters
  const filteredDisasters = mockDisasters.filter((disaster) => {
    // Type filter
    if (!filters.types[disaster.type]) return false;
    
    // Magnitude filter for earthquakes
    if (disaster.type === "earthquake" && disaster.magnitude) {
      if (disaster.magnitude < filters.minMagnitude) return false;
    }
    
    return true;
  });

  if (currentScreen === "splash") {
    return (
      <div className="min-h-screen flex items-center justify-center bg-[#fafafa]">
        <div className="w-full max-w-md h-screen">
          <SplashScreen onGetStarted={handleGetStarted} />
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen flex items-center justify-center mesh-gradient">
      {/* Mobile viewport container - premium macOS style */}
      <div className="w-full max-w-md h-screen flex flex-col relative overflow-hidden">
        {/* Background with colorful gradients */}
        <div className="absolute inset-0 overflow-hidden pointer-events-none">
          {/* Animated gradient orbs */}
          <motion.div
            className="absolute w-[600px] h-[600px] rounded-full -top-48 -left-48"
            style={{
              background: "radial-gradient(circle, rgba(196, 255, 13, 0.25) 0%, transparent 70%)",
              filter: "blur(80px)",
            }}
            animate={{
              x: [0, 30, 0],
              y: [0, 40, 0],
            }}
            transition={{
              duration: 20,
              repeat: Infinity,
              ease: "easeInOut",
            }}
          />
          <motion.div
            className="absolute w-[500px] h-[500px] rounded-full -bottom-32 -right-32"
            style={{
              background: "radial-gradient(circle, rgba(100, 200, 255, 0.2) 0%, transparent 70%)",
              filter: "blur(80px)",
            }}
            animate={{
              x: [0, -40, 0],
              y: [0, -30, 0],
            }}
            transition={{
              duration: 25,
              repeat: Infinity,
              ease: "easeInOut",
            }}
          />
          <motion.div
            className="absolute w-[400px] h-[400px] rounded-full top-1/3 right-0"
            style={{
              background: "radial-gradient(circle, rgba(255, 150, 200, 0.15) 0%, transparent 70%)",
              filter: "blur(80px)",
            }}
            animate={{
              x: [0, -20, 0],
              y: [0, 30, 0],
            }}
            transition={{
              duration: 18,
              repeat: Infinity,
              ease: "easeInOut",
            }}
          />
        </div>
        
        <Toaster position="top-center" />
        
        {/* Main content */}
        <div className="flex-1 overflow-hidden">
          <AnimatePresence mode="wait">
            {currentScreen === "home" && (
              <motion.div
                key="home"
                initial={{ opacity: 0, x: -20 }}
                animate={{ opacity: 1, x: 0 }}
                exit={{ opacity: 0, x: 20 }}
                transition={{ duration: 0.3 }}
              >
                <HomeScreen
                  disasters={filteredDisasters}
                  onFilterClick={() => setShowFilters(true)}
                />
              </motion.div>
            )}
            {currentScreen === "insights" && (
              <motion.div
                key="insights"
                initial={{ opacity: 0, x: -20 }}
                animate={{ opacity: 1, x: 0 }}
                exit={{ opacity: 0, x: 20 }}
                transition={{ duration: 0.3 }}
              >
                <InsightsScreen />
              </motion.div>
            )}
            {currentScreen === "settings" && (
              <motion.div
                key="settings"
                initial={{ opacity: 0, x: -20 }}
                animate={{ opacity: 1, x: 0 }}
                exit={{ opacity: 0, x: 20 }}
                transition={{ duration: 0.3 }}
              >
                <SettingsScreen />
              </motion.div>
            )}
            {currentScreen === "about" && (
              <motion.div
                key="about"
                initial={{ opacity: 0, x: -20 }}
                animate={{ opacity: 1, x: 0 }}
                exit={{ opacity: 0, x: 20 }}
                transition={{ duration: 0.3 }}
              >
                <AboutScreen />
              </motion.div>
            )}
          </AnimatePresence>
        </div>

        {/* Bottom Navigation - macOS style */}
        <nav className="liquid-glass border-t border-black/5 px-2 py-3 backdrop-blur-3xl">
          <div className="flex items-center justify-around">
            <NavButton
              icon={Home}
              label="Home"
              active={currentScreen === "home"}
              onClick={() => setCurrentScreen("home")}
            />
            <NavButton
              icon={BarChart3}
              label="Insights"
              active={currentScreen === "insights"}
              onClick={() => setCurrentScreen("insights")}
            />
            <NavButton
              icon={Search}
              label="Filters"
              active={showFilters}
              onClick={() => setShowFilters(true)}
            />
            <NavButton
              icon={Settings}
              label="Settings"
              active={currentScreen === "settings"}
              onClick={() => setCurrentScreen("settings")}
            />
            <NavButton
              icon={Info}
              label="About"
              active={currentScreen === "about"}
              onClick={() => setCurrentScreen("about")}
            />
          </div>
        </nav>

        {/* Filters sheet */}
        <AnimatePresence>
          {showFilters && (
            <FiltersScreen
              onClose={() => setShowFilters(false)}
              onApply={handleApplyFilters}
            />
          )}
        </AnimatePresence>
      </div>
    </div>
  );
}

interface NavButtonProps {
  icon: React.ElementType;
  label: string;
  active: boolean;
  onClick: () => void;
}

function NavButton({ icon: Icon, label, active, onClick }: NavButtonProps) {
  return (
    <motion.button
      onClick={onClick}
      whileTap={{ scale: 0.92 }}
      className={`flex flex-col items-center gap-1 px-4 py-2 rounded-2xl transition-all min-w-[60px] ${
        active ? "bg-[#C4FF0D]/10" : ""
      }`}
    >
      <Icon
        className={`w-6 h-6 transition-colors ${
          active ? "text-[#C4FF0D]" : "text-black/50"
        }`}
      />
      <span
        className={`text-xs font-medium transition-colors ${
          active ? "text-black" : "text-black/40"
        }`}
      >
        {label}
      </span>
    </motion.button>
  );
}
