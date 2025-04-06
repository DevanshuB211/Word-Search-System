import React, { useState } from 'react';
import axios from 'axios';

const WordSearch = () => {
  const [prefix, setPrefix] = useState('');
  const [suggestions, setSuggestions] = useState([]);
  const [message, setMessage] = useState('');

  const apiUrl = 'http://localhost:8080/api';

  const loadWords = async () => {
    try {
      const response = await axios.get(`${apiUrl}/load`);
      setMessage(response.data);
      console.log('Load successful:', response.data);
    } catch (error) {
      setMessage('Error loading words');
      console.error('Load error:', error);
    }
  };

  const handleAutocomplete = async (e) => {
    const value = e.target.value;
    setPrefix(value);
    if (value) {
      try {
        const response = await axios.get(`${apiUrl}/autocomplete`, { params: { prefix: value } });
        setSuggestions(response.data);
        console.log('Autocomplete successful:', response.data);
      } catch (error) {
        setSuggestions([]);
        console.error('Autocomplete error:', error);
      }
    } else {
      setSuggestions([]);
    }
  };

  return (
    <div className="row justify-content-center">
      <div className="col-md-6">
        <button className="btn btn-success mb-3 w-100" onClick={loadWords}>
          Load Words
        </button>
        <div className="mb-3">
          <label htmlFor="prefixInput" className="form-label">Autocomplete</label>
          <input
            id="prefixInput"
            type="text"
            className="form-control"
            value={prefix}
            onChange={handleAutocomplete}
            placeholder="Enter prefix (e.g., 'he')"
          />
          {suggestions.length > 0 && (
            <ul className="list-group mt-2">
              {suggestions.map((suggestion, index) => (
                <li key={index} className="list-group-item">{suggestion}</li>
              ))}
            </ul>
          )}
        </div>
        {message && <p className="text-info">{message}</p>}
      </div>
    </div>
  );
};

export default WordSearch;