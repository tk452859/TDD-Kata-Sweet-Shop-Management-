import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [sweets, setSweets] = useState([]);
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [token, setToken] = useState('');
  const [user, setUser] = useState(null);

  // Register user
  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/api/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password })
      });
      const data = await response.json();
      setToken(data.token);
      setUser({ email: data.email, role: data.role });
      setIsLoggedIn(true);
      fetchSweets(data.token);
    } catch (error) {
      alert('Registration failed');
    }
  };

  // Login user
  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password })
      });
      const data = await response.json();
      setToken(data.token);
      setUser({ email: data.email, role: data.role });
      setIsLoggedIn(true);
      fetchSweets(data.token);
    } catch (error) {
      alert('Login failed');
    }
  };

  // Fetch sweets from backend
  const fetchSweets = async (authToken) => {
    try {
      const response = await fetch('http://localhost:8080/api/sweets', {
        headers: { 'Authorization': `Bearer ${authToken}` }
      });
      const data = await response.json();
      setSweets(data);
    } catch (error) {
      console.log('Using demo data');
      setSweets([
        { id: 1, name: 'Chocolate Bar', category: 'Chocolate', price: 2.50, quantity: 100 },
        { id: 2, name: 'Gummy Bears', category: 'Gummies', price: 1.50, quantity: 50 },
        { id: 3, name: 'Lollipop', category: 'Candy', price: 0.75, quantity: 200 }
      ]);
    }
  };

  // Purchase sweet
  const purchaseSweet = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/api/sweets/${id}/purchase?quantity=1`, {
        method: 'POST',
        headers: { 'Authorization': `Bearer ${token}` }
      });
      if (response.ok) {
        alert('Purchase successful!');
        fetchSweets(token);
      }
    } catch (error) {
      alert('Purchase failed - using demo');
    }
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>🍬 Sweet Shop Management</h1>
        
        {!isLoggedIn ? (
          <div className="auth-section">
            <div className="auth-form">
              <h2>Register</h2>
              <form onSubmit={handleRegister}>
                <input 
                  type="email" 
                  placeholder="Email" 
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required 
                />
                <input 
                  type="password" 
                  placeholder="Password" 
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required 
                />
                <button type="submit">Register</button>
              </form>
            </div>

            <div className="auth-form">
              <h2>Login</h2>
              <form onSubmit={handleLogin}>
                <input 
                  type="email" 
                  placeholder="Email" 
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required 
                />
                <input 
                  type="password" 
                  placeholder="Password" 
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required 
                />
                <button type="submit">Login</button>
              </form>
            </div>
          </div>
        ) : (
          <div className="dashboard">
            <div className="user-info">
              <h2>Welcome, {user?.email}!</h2>
              <p>Role: {user?.role}</p>
              <button onClick={() => setIsLoggedIn(false)}>Logout</button>
            </div>

            <div className="sweets-section">
              <h3>Available Sweets</h3>
              <div className="sweets-grid">
                {sweets.map(sweet => (
                  <div key={sweet.id} className="sweet-card">
                    <h4>{sweet.name}</h4>
                    <p>Category: {sweet.category}</p>
                    <p>Price: ${sweet.price}</p>
                    <p>Quantity: {sweet.quantity}</p>
                    <button 
                      onClick={() => purchaseSweet(sweet.id)}
                      disabled={sweet.quantity === 0}
                      className={sweet.quantity === 0 ? 'disabled' : ''}
                    >
                      {sweet.quantity === 0 ? 'Out of Stock' : 'Purchase'}
                    </button>
                  </div>
                ))}
              </div>
            </div>
          </div>
        )}
      </header>
    </div>
  );
}

export default App;