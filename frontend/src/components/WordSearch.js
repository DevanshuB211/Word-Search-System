import React, { useState } from 'react';
import axios from 'axios';

const WordSearch = () => {
  const [prefix, setPrefix] = useState('');
  const [suggestions, setSuggestions] = useState([]);
  const [searchWord, setSearchWord] = useState('');
  const [rank, setRank] = useState(null);
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

  const handleSearch = async () => {
    if (searchWord) {
      try {
        const searchResponse = await axios.get(`${apiUrl}/search`, { params: { word: searchWord } });
        if (searchResponse.data) {
          const rankResponse = await axios.get(`${apiUrl}/rank`, { params: { word: searchWord } });
          setRank(rankResponse.data);
          setMessage(`Searched: ${searchWord}`);
          console.log('Search successful, rank:', rankResponse.data);
        } else {
          setMessage('Word not found');
          setRank(null);
        }
      } catch (error) {
        setMessage('Error during search');
        setRank(null);
        console.error('Search error:', error);
      }
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
        <div className="mb-3">
          <label htmlFor="searchInput" className="form-label">Search Word</label>
          <input
            id="searchInput"
            type="text"
            className="form-control"
            value={searchWord}
            onChange={(e) => setSearchWord(e.target.value)}
            placeholder="Enter word (e.g., 'hello')"
          />
          <button className="btn btn-primary mt-2 w-100" onClick={handleSearch}>
            Search
          </button>
        </div>
        {message && <p className="text-info">{message}</p>}
        {rank !== null && <p className="text-success">Rank: {rank}</p>}
      </div>
    </div>
  );
};

export default WordSearch;