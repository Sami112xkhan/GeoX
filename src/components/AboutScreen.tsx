import { motion } from "motion/react";
import { Globe, Shield, Zap, Heart } from "lucide-react";

export function AboutScreen() {
  return (
    <div className="h-full overflow-y-auto">
      <div className="p-5 space-y-4">
        {/* Header */}
        <div className="text-center mb-6">
          <motion.div
            className="w-20 h-20 rounded-full mx-auto mb-4 flex items-center justify-center lime-glow-strong premium-shadow relative overflow-hidden"
            style={{
              background: "linear-gradient(135deg, rgba(196, 255, 13, 0.8) 0%, rgba(196, 255, 13, 0.6) 100%)",
            }}
            initial={{ scale: 0, rotate: -180 }}
            animate={{ scale: 1, rotate: 0 }}
            transition={{ type: "spring", duration: 0.8 }}
          >
            <Globe className="w-10 h-10 text-black relative z-10" />
            <div className="absolute inset-0 bg-white/20 rounded-full" />
          </motion.div>
          <h1 className="text-black mb-2">GeoX</h1>
          <p className="text-black/60">Disaster Intelligence. Simplified.</p>
          <p className="text-black/40 text-sm mt-1 font-medium">Version 1.0.0</p>
        </div>

        {/* Mission */}
        <motion.div
          className="liquid-glass rounded-3xl p-5 premium-shadow"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.1 }}
        >
          <h3 className="text-black mb-2 font-semibold">Our Mission</h3>
          <p className="text-black/60 text-sm leading-relaxed">
            GeoX provides real-time disaster intelligence to help communities stay informed
            and prepared. We aggregate data from trusted sources like NASA and USGS to
            deliver accurate, timely information about natural events worldwide.
          </p>
        </motion.div>

        {/* Features */}
        <motion.div
          className="liquid-glass rounded-3xl p-5 premium-shadow"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.2 }}
        >
          <h3 className="text-black mb-4 font-semibold">Features</h3>
          <div className="space-y-3">
            <FeatureItem
              icon={Globe}
              title="Real-time Tracking"
              description="Monitor disasters as they happen globally"
              color="bg-[#C4FF0D]"
            />
            <FeatureItem
              icon={Zap}
              title="AI Predictions"
              description="Advanced forecasting for disaster hotspots"
              color="bg-yellow-500"
            />
            <FeatureItem
              icon={Shield}
              title="Trusted Sources"
              description="Data from NASA EONET, USGS, and NOAA"
              color="bg-green-500"
            />
          </div>
        </motion.div>

        {/* Data Sources */}
        <motion.div
          className="liquid-glass rounded-3xl p-5 premium-shadow"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.3 }}
        >
          <h3 className="text-black mb-3 font-semibold">Data Sources</h3>
          <div className="space-y-2">
            <DataSource
              name="NASA EONET"
              description="Earth Observatory Natural Event Tracker"
              url="eonet.gsfc.nasa.gov"
            />
            <DataSource
              name="USGS Earthquake"
              description="United States Geological Survey"
              url="earthquake.usgs.gov"
            />
            <DataSource
              name="NOAA"
              description="National Oceanic and Atmospheric Administration"
              url="noaa.gov"
            />
          </div>
        </motion.div>

        {/* Team */}
        <motion.div
          className="liquid-glass rounded-3xl p-5 premium-shadow"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.4 }}
        >
          <div className="text-center">
            <Heart className="w-8 h-8 text-red-500 mx-auto mb-2" />
            <p className="text-black/60 text-sm">
              Built with care by GeoX Labs
            </p>
            <p className="text-black/40 text-xs mt-2">
              © 2025 GeoX Labs. All rights reserved.
            </p>
          </div>
        </motion.div>

        {/* Contact */}
        <motion.div
          className="text-center text-black/40 text-sm pb-4"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.5 }}
        >
          <p className="font-medium">Contact: hello@geox.app</p>
        </motion.div>
      </div>
    </div>
  );
}

interface FeatureItemProps {
  icon: React.ElementType;
  title: string;
  description: string;
  color: string;
}

function FeatureItem({ icon: Icon, title, description, color }: FeatureItemProps) {
  return (
    <div className="flex items-start gap-3">
      <div className={`${color} w-10 h-10 rounded-xl flex items-center justify-center flex-shrink-0 shadow-md`}>
        <Icon className="w-5 h-5 text-black" />
      </div>
      <div>
        <h4 className="text-black text-sm mb-0.5 font-semibold">{title}</h4>
        <p className="text-black/60 text-xs">{description}</p>
      </div>
    </div>
  );
}

interface DataSourceProps {
  name: string;
  description: string;
  url: string;
}

function DataSource({ name, description, url }: DataSourceProps) {
  return (
    <div className="liquid-glass-subtle rounded-2xl p-3">
      <h4 className="text-black text-sm mb-0.5 font-semibold">{name}</h4>
      <p className="text-black/60 text-xs mb-1">{description}</p>
      <p className="text-[#C4FF0D] text-xs font-medium">{url}</p>
    </div>
  );
}
