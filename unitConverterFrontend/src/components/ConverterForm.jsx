import { useState, useEffect } from "react";

function ConverterForm({ conversionType }) {
    const [units, setUnits] = useState([]);
    const [value, setValue] = useState("");
    const [fromUnit, setFromUnit] = useState("");
    const [toUnit, setToUnit] = useState("");
    const [result, setResult] = useState(null);
    const [error, setError] = useState("");

    // Fetch available units from backend
    useEffect(() => {
        fetch(`http://localhost:8080/units/${conversionType}`)
            .then((response) => response.json())
            .then((data) => setUnits(data))
            .catch(() => setError("Failed to load units."));
    }, [conversionType]);

    const handleConvert = () => {
        // Validate input
        if (!value || !fromUnit || !toUnit) {
            setError("Please enter a value and select both units.");
            return;
        }
        if (fromUnit === toUnit) {
            setError("The 'from' and 'to' units cannot be the same.");
            return;
        }

        setError(""); // Clear errors before making a request

        const requestBody = {
            "type": conversionType,
            "value": value,
            "from": fromUnit,
            "to": toUnit,
        }

        fetch(`http://localhost:8080/convert`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(requestBody),
        })
            .then((response) => response.json())
            .then((data) => setResult(data))
            .catch(() => setError("Conversion failed. Try again."));
    };

    if(result === null) {
        return (
            <div className="converter-container">
                <h2>{conversionType.charAt(0).toUpperCase() + conversionType.slice(1)} Converter</h2>

                {error && <p style={{ color: "red" }}>{error}</p>}

                <input
                    type="number"
                    value={value}
                    onChange={(e) => setValue(e.target.value)}
                    placeholder="Enter value"
                />

                <select onChange={(e) => setFromUnit(e.target.value)}>
                    <option value="">From</option>
                    {units.map((unit) => (
                        <option key={unit} value={unit}>{unit}</option>
                    ))}
                </select>

                <select onChange={(e) => setToUnit(e.target.value)}>
                    <option value="">To</option>
                    {units.map((unit) => (
                        <option key={unit} value={unit}>{unit}</option>
                    ))}
                </select>

                <button onClick={handleConvert}>Convert</button>

                {result !== null && <p className="result">{result.convertedValue} {toUnit}</p>}
            </div>
        );
    }else{
        return(
            <div className="converter-container">
                <h2>Result of your calculation</h2>
                <p className="result">{result.originalValue.toFixed(2)} {result.from} = {result.convertedValue.toFixed(2)} {result.to}</p>
                <button onClick={() => setResult(null)}>Reset</button>
            </div>
        )
    }

}

export default ConverterForm;
