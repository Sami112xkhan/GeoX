import { motion } from "motion/react";
import { Button } from "./ui/button";

interface SplashScreenProps {
  onGetStarted: () => void;
}

export function SplashScreen({ onGetStarted }: SplashScreenProps) {
  return (
    <div className="fixed inset-0 flex items-center justify-center overflow-hidden">
      {/* Vibrant gradient background */}
      <div className="absolute inset-0 bg-gradient-to-br from-green-50 via-yellow-50 to-blue-50" />
      
      {/* Animated ambient orbs */}
      <div className="absolute inset-0 overflow-hidden">
        <motion.div
          className="absolute w-[500px] h-[500px] rounded-full"
          style={{
            background: "radial-gradient(circle, rgba(196, 255, 13, 0.3) 0%, transparent 70%)",
            filter: "blur(60px)",
          }}
          animate={{
            x: ["-25%", "25%", "-25%"],
            y: ["-10%", "10%", "-10%"],
          }}
          transition={{
            duration: 20,
            repeat: Infinity,
            ease: "easeInOut",
          }}
        />
        <motion.div
          className="absolute w-[400px] h-[400px] rounded-full right-0 bottom-0"
          style={{
            background: "radial-gradient(circle, rgba(100, 200, 255, 0.25) 0%, transparent 70%)",
            filter: "blur(80px)",
          }}
          animate={{
            x: ["10%", "-10%", "10%"],
            y: ["10%", "-10%", "10%"],
          }}
          transition={{
            duration: 25,
            repeat: Infinity,
            ease: "easeInOut",
          }}
        />
        <motion.div
          className="absolute w-[350px] h-[350px] rounded-full left-1/4 top-1/3"
          style={{
            background: "radial-gradient(circle, rgba(255, 150, 200, 0.2) 0%, transparent 70%)",
            filter: "blur(70px)",
          }}
          animate={{
            x: ["-15%", "15%", "-15%"],
            y: ["15%", "-15%", "15%"],
          }}
          transition={{
            duration: 22,
            repeat: Infinity,
            ease: "easeInOut",
          }}
        />
      </div>

      {/* Main content */}
      <div className="relative z-10 flex flex-col items-center gap-12 px-8">
        {/* 3D Liquid Glass Sphere */}
        <motion.div
          className="relative"
          initial={{ opacity: 0, scale: 0.8, y: 20 }}
          animate={{ opacity: 1, scale: 1, y: 0 }}
          transition={{ duration: 1.2, ease: [0.16, 1, 0.3, 1] }}
        >
          {/* Main sphere */}
          <motion.div
            className="w-48 h-48 rounded-full relative"
            style={{
              background: "linear-gradient(135deg, rgba(255, 255, 255, 0.95) 0%, rgba(255, 255, 255, 0.75) 100%)",
              backdropFilter: "blur(60px) saturate(180%)",
              WebkitBackdropFilter: "blur(60px) saturate(180%)",
              border: "2px solid rgba(255, 255, 255, 0.8)",
              boxShadow: `
                0 25px 70px rgba(0, 0, 0, 0.12),
                inset 0 15px 40px rgba(255, 255, 255, 1),
                inset 0 -15px 40px rgba(0, 0, 0, 0.08),
                0 0 0 1px rgba(0, 0, 0, 0.04)
              `,
            }}
            animate={{
              y: [0, -10, 0],
            }}
            transition={{
              duration: 4,
              repeat: Infinity,
              ease: "easeInOut",
            }}
          >
            {/* Highlight */}
            <div
              className="absolute top-6 left-6 w-24 h-24 rounded-full"
              style={{
                background: "radial-gradient(circle at 30% 30%, rgba(255, 255, 255, 0.8) 0%, transparent 70%)",
              }}
            />
            
            {/* Lime accent blob inside */}
            <motion.div
              className="absolute top-1/2 left-1/2 w-20 h-20 rounded-full"
              style={{
                background: "radial-gradient(circle, rgba(196, 255, 13, 0.6) 0%, rgba(196, 255, 13, 0.2) 70%)",
                filter: "blur(20px)",
                transform: "translate(-50%, -50%)",
              }}
              animate={{
                scale: [1, 1.2, 1],
                opacity: [0.6, 0.8, 0.6],
              }}
              transition={{
                duration: 3,
                repeat: Infinity,
                ease: "easeInOut",
              }}
            />

            {/* Rotating ring */}
            <motion.div
              className="absolute inset-0"
              animate={{ rotate: 360 }}
              transition={{ duration: 30, repeat: Infinity, ease: "linear" }}
            >
              <div
                className="absolute top-1/2 left-1/2 w-56 h-2 rounded-full"
                style={{
                  background: "linear-gradient(90deg, transparent 0%, rgba(196, 255, 13, 0.3) 50%, transparent 100%)",
                  transform: "translate(-50%, -50%)",
                  filter: "blur(1px)",
                }}
              />
            </motion.div>
          </motion.div>

          {/* Glow effect */}
          <div
            className="absolute inset-0 w-48 h-48 rounded-full"
            style={{
              background: "radial-gradient(circle, rgba(196, 255, 13, 0.2) 0%, transparent 70%)",
              filter: "blur(40px)",
            }}
          />
        </motion.div>

        {/* Title */}
        <motion.div
          className="text-center"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.4, duration: 0.8, ease: [0.16, 1, 0.3, 1] }}
        >
          <h1 className="text-7xl mb-3 text-black tracking-tight">GeoX</h1>
          <p className="text-black/60 text-lg">Disaster Intelligence. Simplified.</p>
        </motion.div>

        {/* Get Started button */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.8, duration: 0.8, ease: [0.16, 1, 0.3, 1] }}
        >
          <Button
            onClick={onGetStarted}
            className="liquid-glass hover:bg-white/90 active:bg-white/80 rounded-full px-8 py-6 text-black border border-black/5 premium-shadow transition-all duration-200"
          >
            <span className="font-semibold">Get Started</span>
          </Button>
        </motion.div>

        {/* Floating particles */}
        {[...Array(15)].map((_, i) => (
          <motion.div
            key={i}
            className="absolute w-1 h-1 bg-[#C4FF0D]/30 rounded-full"
            style={{
              left: `${Math.random() * 100}%`,
              top: `${Math.random() * 100}%`,
            }}
            animate={{
              y: [0, -30, 0],
              opacity: [0.2, 0.6, 0.2],
            }}
            transition={{
              duration: 3 + Math.random() * 2,
              repeat: Infinity,
              delay: Math.random() * 2,
            }}
          />
        ))}
      </div>
    </div>
  );
}
