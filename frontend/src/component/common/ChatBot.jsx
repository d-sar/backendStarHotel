import React, { useState, useRef, useEffect } from 'react';
import './ChatBot.css';
import ApiService from '../../service/ApiService';

function ChatBot() {
    const [isOpen, setIsOpen] = useState(false);
    const [messages, setMessages] = useState([
        { type: 'bot', text: 'Hello! How can I help you with your hotel booking today?' }
    ]);
    const [inputMessage, setInputMessage] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const messagesEndRef = useRef(null);

    const scrollToBottom = () => {
        messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' });
    };

    useEffect(() => {
        scrollToBottom();
    }, [messages]);

    const toggleChat = () => {
        setIsOpen(!isOpen);
    };

    const handleSendMessage = async () => {
        if (!inputMessage.trim() || isLoading) return;

        const userMessage = inputMessage.trim();
        setInputMessage('');
        
        // Add user message to chat
        setMessages(prev => [...prev, { type: 'user', text: userMessage }]);
        setIsLoading(true);

        try {
            const stream = await ApiService.sendChatMessage(userMessage);
            const reader = stream.getReader();
            const decoder = new TextDecoder();
            let botResponse = '';

            // Add empty bot message that will be updated
            setMessages(prev => [...prev, { type: 'bot', text: '', isStreaming: true }]);

            while (true) {
                const { done, value } = await reader.read();
                if (done) break;

                const chunk = decoder.decode(value, { stream: true });
                botResponse += chunk;

                // Update the last message (bot response) with streamed content
                setMessages(prev => {
                    const updated = [...prev];
                    updated[updated.length - 1] = { type: 'bot', text: botResponse, isStreaming: true };
                    return updated;
                });
            }

            // Mark streaming as complete
            setMessages(prev => {
                const updated = [...prev];
                updated[updated.length - 1] = { type: 'bot', text: botResponse, isStreaming: false };
                return updated;
            });

        } catch (error) {
            console.error('Error sending message:', error);
            setMessages(prev => [
                ...prev,
                { type: 'bot', text: 'Sorry, I encountered an error. Please try again later.' }
            ]);
        } finally {
            setIsLoading(false);
        }
    };

    const handleKeyPress = (e) => {
        if (e.key === 'Enter' && !e.shiftKey) {
            e.preventDefault();
            handleSendMessage();
        }
    };

    return (
        <div className="chatbot-container">
            {/* Chat Toggle Button */}
            <button 
                className={`chat-toggle-btn ${isOpen ? 'open' : ''}`}
                onClick={toggleChat}
                aria-label="Toggle chat"
            >
                {isOpen ? '✕' : '💬'}
            </button>

            {/* Chat Window */}
            {isOpen && (
                <div className="chat-window">
                    <div className="chat-header">
                        <h3>Hotel Assistant</h3>
                        <button className="close-btn" onClick={toggleChat}>✕</button>
                    </div>

                    <div className="chat-messages">
                        {messages.map((message, index) => (
                            <div 
                                key={index} 
                                className={`message ${message.type}`}
                            >
                                <div className="message-content">
                                    {message.text}
                                    {message.isStreaming && <span className="cursor">|</span>}
                                </div>
                            </div>
                        ))}
                        {isLoading && messages[messages.length - 1]?.type !== 'bot' && (
                            <div className="message bot">
                                <div className="message-content typing-indicator">
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                </div>
                            </div>
                        )}
                        <div ref={messagesEndRef} />
                    </div>

                    <div className="chat-input-container">
                        <input
                            type="text"
                            className="chat-input"
                            placeholder="Type your message..."
                            value={inputMessage}
                            onChange={(e) => setInputMessage(e.target.value)}
                            onKeyPress={handleKeyPress}
                            disabled={isLoading}
                        />
                        <button 
                            className="send-btn" 
                            onClick={handleSendMessage}
                            disabled={isLoading || !inputMessage.trim()}
                        >
                            {isLoading ? '⏳' : '➤'}
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
}

export default ChatBot;