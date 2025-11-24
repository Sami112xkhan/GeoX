import { useState } from "react";
import { motion } from "motion/react";
import {
  LineChart,
  Line,
  BarChart,
  Bar,
  PieChart,
  Pie,
  Cell,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
  Legend,
} from "recharts";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "./ui/select";

const frequencyData = [
  { day: "Day 1", count: 12 },
  { day: "Day 5", count: 19 },
  { day: "Day 10", count: 8 },
  { day: "Day 15", count: 22 },
  { day: "Day 20", count: 15 },
  { day: "Day 25", count: 28 },
  { day: "Day 30", count: 18 },
];

const magnitudeData = [
  { range: "2-3", count: 45 },
  { range: "3-4", count: 32 },
  { range: "4-5", count: 18 },
  { range: "5-6", count: 8 },
  { range: "6+", count: 3 },
];

const categoryData = [
  { name: "Earthquakes", value: 48, color: "#ef4444" },
  { name: "Wildfires", value: 28, color: "#f97316" },
  { name: "Storms", value: 15, color: "#06b6d4" },
  { name: "Floods", value: 9, color: "#3b82f6" },
];

export function InsightsScreen() {
  const [region, setRegion] = useState("global");

  return (
    <div className="h-full overflow-y-auto">
      <div className="p-5 space-y-4">
        {/* Header */}
        <div className="flex items-center justify-between mb-2">
          <h2 className="text-black">Insights & Analytics</h2>
          <Select value={region} onValueChange={setRegion}>
            <SelectTrigger className="w-40 liquid-glass border-black/5 text-black rounded-2xl h-10">
              <SelectValue />
            </SelectTrigger>
            <SelectContent className="liquid-glass border-black/5">
              <SelectItem value="global">Global</SelectItem>
              <SelectItem value="asia">Asia</SelectItem>
              <SelectItem value="europe">Europe</SelectItem>
              <SelectItem value="americas">Americas</SelectItem>
              <SelectItem value="africa">Africa</SelectItem>
            </SelectContent>
          </Select>
        </div>

        {/* Frequency chart */}
        <motion.div
          className="liquid-glass rounded-3xl p-5 premium-shadow"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.1 }}
        >
          <h3 className="text-black mb-4 font-semibold">Earthquake Frequency (30 Days)</h3>
          <ResponsiveContainer width="100%" height={200}>
            <LineChart data={frequencyData}>
              <CartesianGrid strokeDasharray="3 3" stroke="rgba(0,0,0,0.05)" />
              <XAxis dataKey="day" stroke="#00000080" fontSize={12} />
              <YAxis stroke="#00000080" fontSize={12} />
              <Tooltip
                contentStyle={{
                  backgroundColor: "rgba(255, 255, 255, 0.95)",
                  border: "1px solid rgba(0,0,0,0.05)",
                  borderRadius: "12px",
                  color: "#000",
                  backdropFilter: "blur(20px)",
                }}
              />
              <Line
                type="monotone"
                dataKey="count"
                stroke="#C4FF0D"
                strokeWidth={3}
                dot={{ fill: "#C4FF0D", r: 4, strokeWidth: 2, stroke: "#fff" }}
                activeDot={{ r: 6 }}
              />
            </LineChart>
          </ResponsiveContainer>
        </motion.div>

        {/* Magnitude distribution */}
        <motion.div
          className="liquid-glass rounded-3xl p-5 premium-shadow"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.2 }}
        >
          <h3 className="text-black mb-4 font-semibold">Magnitude Distribution</h3>
          <ResponsiveContainer width="100%" height={200}>
            <BarChart data={magnitudeData}>
              <CartesianGrid strokeDasharray="3 3" stroke="rgba(0,0,0,0.05)" />
              <XAxis dataKey="range" stroke="#00000080" fontSize={12} />
              <YAxis stroke="#00000080" fontSize={12} />
              <Tooltip
                contentStyle={{
                  backgroundColor: "rgba(255, 255, 255, 0.95)",
                  border: "1px solid rgba(0,0,0,0.05)",
                  borderRadius: "12px",
                  color: "#000",
                  backdropFilter: "blur(20px)",
                }}
              />
              <Bar dataKey="count" fill="#C4FF0D" radius={[8, 8, 0, 0]} />
            </BarChart>
          </ResponsiveContainer>
        </motion.div>

        {/* Category pie chart */}
        <motion.div
          className="liquid-glass rounded-3xl p-5 premium-shadow"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.3 }}
        >
          <h3 className="text-black mb-4 font-semibold">Disasters by Category</h3>
          <ResponsiveContainer width="100%" height={250}>
            <PieChart>
              <Pie
                data={categoryData}
                cx="50%"
                cy="50%"
                labelLine={false}
                label={({ name, percent }) =>
                  `${name} ${(percent * 100).toFixed(0)}%`
                }
                outerRadius={80}
                fill="#8884d8"
                dataKey="value"
              >
                {categoryData.map((entry, index) => (
                  <Cell key={`cell-${index}`} fill={entry.color} />
                ))}
              </Pie>
              <Tooltip
                contentStyle={{
                  backgroundColor: "rgba(255, 255, 255, 0.95)",
                  border: "1px solid rgba(0,0,0,0.05)",
                  borderRadius: "12px",
                  color: "#000",
                  backdropFilter: "blur(20px)",
                }}
              />
            </PieChart>
          </ResponsiveContainer>
        </motion.div>

        {/* AI Forecast */}
        <motion.div
          className="liquid-glass rounded-3xl p-5 border-2 border-[#C4FF0D]/20 lime-glow premium-shadow"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.4 }}
        >
          <h3 className="text-black mb-2 font-semibold">AI Forecast</h3>
          <p className="text-black/60 text-sm mb-4">Predicted hotspots next week</p>

          {/* Mini heatmap representation */}
          <div className="grid grid-cols-7 gap-2 mb-4">
            {[...Array(35)].map((_, i) => {
              const intensity = Math.random();
              return (
                <motion.div
                  key={i}
                  className="aspect-square rounded-lg"
                  style={{
                    backgroundColor:
                      intensity > 0.7
                        ? "#ef4444"
                        : intensity > 0.4
                        ? "#f97316"
                        : "rgba(0,0,0,0.05)",
                  }}
                  initial={{ opacity: 0, scale: 0 }}
                  animate={{ opacity: 1, scale: 1 }}
                  transition={{ delay: 0.4 + i * 0.02 }}
                />
              );
            })}
          </div>

          <div className="flex items-center gap-4 text-xs">
            <div className="flex items-center gap-2">
              <div className="w-3 h-3 rounded bg-red-500" />
              <span className="text-black/60 font-medium">High Risk</span>
            </div>
            <div className="flex items-center gap-2">
              <div className="w-3 h-3 rounded bg-orange-500" />
              <span className="text-black/60 font-medium">Medium Risk</span>
            </div>
            <div className="flex items-center gap-2">
              <div className="w-3 h-3 rounded bg-black/5" />
              <span className="text-black/60 font-medium">Low Risk</span>
            </div>
          </div>
        </motion.div>
      </div>
    </div>
  );
}
