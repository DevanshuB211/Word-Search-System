import React from 'react';
import WordSearch from './components/WordSearch';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header bg-primary text-white py-3">
        <h1>Word Search System</h1>
      </header>
      <main className="container mt-4">
        <WordSearch />
      </main>
    </div>
  );
}

export default App;