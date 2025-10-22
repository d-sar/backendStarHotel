import React, { useState } from "react";
import RoomResult from "../common/RoomResult";
import RoomSearch from "../common/RoomSearch";
import "../home/style.css";




const HomePage = () => {

    const [roomSearchResults, setRoomSearchResults] = useState([]);

    // Function to handle search results
    const handleSearchResult = (results) => {
        setRoomSearchResults(results);
    };

    return (
        <div className="home">
            {/* HEADER / BANNER ROOM SECTION */}
            <section>
                <header className="header-banner">
                    <div className="header-content">
                        <img src="./assets/images/hotel.png" alt="Itran Hotel" className="header-image" />
                        <div className="text-container">
                            <h1>
                                Welcome to <span className="phegon-color">Itran Hotel</span>
                            </h1>
                            <h3>Step into a haven of comfort and care</h3>
                        </div>
                    </div>
                </header>
            </section>


            {/* SEARCH/FIND AVAILABLE ROOM SECTION */}
            <RoomSearch handleSearchResult={handleSearchResult} />
            <RoomResult roomSearchResults={roomSearchResults} />

            <h4><a className="view-rooms-home" href="/rooms">All Rooms</a></h4>

            <h2 className="home-services">Services at <span className="phegon-color">Itran Hotel</span></h2>
{/* SERVICES SECTION - FORMAT "GRANDE IMAGE" */}
<section className="service-section service-section-gallery"> 
    
    {/* 1. TABLE D'HÔTES & SAVEURS AMAZIGH */}
    <div className="service-card large-image-card">
        {/* L'image est mise en avant en haut */}
        <div className="service-image-container">
            <img src="./assets/images/tajine.jpg" alt="Cuisine Locale / Tajine" />
        </div>
        
        {/* Le texte est sous l'image */}
        <div className="service-details">
            <h3 className="service-title">Table d'Hôtes & Saveurs Amazigh</h3>
            <p className="service-description">Dégustez des plats berbères authentiques et des recettes régionales, préparés avec des produits frais du terroir. Une expérience culinaire en demi-pension ou pension complète.</p>
        </div>
    </div>
    
    {/* 2. PISCINE & VUE PANORAMIQUE */}
    <div className="service-card large-image-card">
        <div className="service-image-container">
            <img src="./assets/images/piscine.png" alt="Piscine et Vue" />
        </div>
        <div className="service-details">
            <h3 className="service-title">Piscine & Vue Panoramique</h3>
            <p className="service-description">Relaxez-vous dans notre piscine face aux sommets de l'Atlas. Profitez également de notre Hammam traditionnel pour un soin complet.</p>
        </div>
    </div>

    {/* 3. GUIDES LOCAUX & RANDONNÉES */}
    <div className="service-card large-image-card">
        <div className="service-image-container">
            <img src="./assets/images/dromadaire.jpg" alt="Excursions et Randonnées" />
        </div>
        <div className="service-details">
            <h3 className="service-title">Guides Locaux & Randonnées</h3>
            <p className="service-description">Découvrez la beauté du Jbal Atlas Saghir avec des guides Amazigh expérimentés. Excursions à pied, en 4x4 ou à dos de dromadaire disponibles.</p>
        </div>
    </div>

    {/* 4. CONFORT MODERNE & AUTHENTIQUE */}
    <div className="service-card large-image-card">
        <div className="service-image-container">
            <img src="./assets/images/ch9.jpg" alt="Confort et Luxe" />
        </div>
        <div className="service-details">
            <h3 className="service-title">Confort Moderne & Authentique</h3>
            <p className="service-description">Lits king-size, Climatisation/Chauffage silencieux, Salle de bain Tadelakt, et Wi-Fi haut débit gratuit dans toutes les chambres et espaces communs.</p>
        </div>
    </div>

</section>
           

            <section>

            </section>
        </div>
    );
}

export default HomePage;
