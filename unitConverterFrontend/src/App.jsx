import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import LengthConverter from "./pages/LengthConverter";
import WeightConverter from "./pages/WeightConverter";
import TemperatureConverter from "./pages/TemperatureConverter";

function App() {
    return (
        <Router>
            <div>
                <h1>Unit Converter</h1>
                <nav>
                    <Link to="/length">Length</Link> |
                    <Link to="/weight">Weight</Link> |
                    <Link to="/temperature">Temperature</Link>
                </nav>
                <Routes>
                    <Route path="/length" element={<LengthConverter/>}/>
                    <Route path="/weight" element={<WeightConverter/>}/>
                    <Route path="/temperature" element={<TemperatureConverter/>}/>
                </Routes>
            </div>
        </Router>
    );
}

export default App
