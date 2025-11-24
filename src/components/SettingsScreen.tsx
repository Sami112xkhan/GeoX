import { useState } from "react";
import { motion } from "motion/react";
import { Moon, Bell, Trash2, Zap, Info } from "lucide-react";
import { Switch } from "./ui/switch";
import { Button } from "./ui/button";
import { toast } from "sonner@2.0.3";

export function SettingsScreen() {
  const [darkMode, setDarkMode] = useState(false);
  const [notifications, setNotifications] = useState(true);

  const handleClearCache = () => {
    toast.success("Cache cleared successfully!");
  };

  const handleSimulateEvent = () => {
    toast.warning("🌋 Simulated: Volcano eruption detected in Indonesia");
  };

  return (
    <div className="h-full overflow-y-auto">
      <div className="p-5 space-y-4">
        {/* Header */}
        <h2 className="text-black mb-2">Settings</h2>

        {/* Settings cards */}
        <motion.div
          className="liquid-glass rounded-3xl p-5 premium-shadow"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.1 }}
        >
          <div className="flex items-center justify-between">
            <div className="flex items-center gap-3">
              <div className="w-11 h-11 bg-black/5 rounded-2xl flex items-center justify-center">
                <Moon className="w-5 h-5 text-black/70" />
              </div>
              <div>
                <h4 className="text-black font-semibold">Dark Mode</h4>
                <p className="text-black/50 text-sm">Enable dark theme</p>
              </div>
            </div>
            <Switch 
              checked={darkMode} 
              onCheckedChange={setDarkMode}
              className="data-[state=checked]:bg-[#C4FF0D]"
            />
          </div>
        </motion.div>

        <motion.div
          className="liquid-glass rounded-3xl p-5 premium-shadow"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.2 }}
        >
          <div className="flex items-center justify-between">
            <div className="flex items-center gap-3">
              <div className="w-11 h-11 bg-orange-500/10 rounded-2xl flex items-center justify-center">
                <Bell className="w-5 h-5 text-orange-500" />
              </div>
              <div>
                <h4 className="text-black font-semibold">Notifications</h4>
                <p className="text-black/50 text-sm">Get alerts for disasters</p>
              </div>
            </div>
            <Switch 
              checked={notifications} 
              onCheckedChange={setNotifications}
              className="data-[state=checked]:bg-[#C4FF0D]"
            />
          </div>
        </motion.div>

        <motion.div
          className="liquid-glass rounded-3xl p-5 premium-shadow"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.3 }}
        >
          <div className="flex items-center justify-between">
            <div className="flex items-center gap-3 flex-1">
              <div className="w-12 h-12 bg-red-500/10 rounded-2xl flex items-center justify-center">
                <Trash2 className="w-5 h-5 text-red-500" />
              </div>
              <div className="flex-1">
                <h4 className="text-black font-semibold">Clear Cache</h4>
                <p className="text-black/50 text-sm">Remove stored data</p>
              </div>
            </div>
            <Button
              onClick={handleClearCache}
              variant="outline"
              size="sm"
              className="liquid-glass-subtle border-black/5 text-black hover:bg-black/5 active:bg-black/10 rounded-xl px-4 py-2 min-h-[40px] font-medium"
            >
              Clear
            </Button>
          </div>
        </motion.div>

        <motion.div
          className="liquid-glass rounded-3xl p-5 premium-shadow"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.4 }}
        >
          <div className="flex items-center justify-between">
            <div className="flex items-center gap-3 flex-1">
              <div className="w-12 h-12 bg-yellow-500/10 rounded-2xl flex items-center justify-center">
                <Zap className="w-5 h-5 text-yellow-500" />
              </div>
              <div className="flex-1">
                <h4 className="text-black font-semibold">Simulate Event</h4>
                <p className="text-black/50 text-sm">Test notifications</p>
              </div>
            </div>
            <Button
              onClick={handleSimulateEvent}
              variant="outline"
              size="sm"
              className="liquid-glass-subtle border-black/5 text-black hover:bg-black/5 active:bg-black/10 rounded-xl px-4 py-2 min-h-[40px] font-medium"
            >
              Test
            </Button>
          </div>
        </motion.div>

        {/* API sources info */}
        <motion.div
          className="liquid-glass rounded-3xl p-5 mt-6 premium-shadow"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.5 }}
        >
          <div className="flex items-start gap-3">
            <Info className="w-5 h-5 text-[#C4FF0D] flex-shrink-0 mt-0.5" />
            <div>
              <h4 className="text-black mb-2 font-semibold">Data Sources</h4>
              <p className="text-black/60 text-sm mb-1">
                • NASA EONET - Earth Observatory Natural Event Tracker
              </p>
              <p className="text-black/60 text-sm mb-1">
                • USGS - Earthquake Hazards Program
              </p>
              <p className="text-black/60 text-sm">
                • NOAA - National Oceanic and Atmospheric Administration
              </p>
            </div>
          </div>
        </motion.div>

        {/* Version */}
        <motion.div
          className="text-center py-4"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.6 }}
        >
          <p className="text-black/40 text-sm font-medium">v1.0 — GeoX Labs</p>
        </motion.div>
      </div>
    </div>
  );
}
