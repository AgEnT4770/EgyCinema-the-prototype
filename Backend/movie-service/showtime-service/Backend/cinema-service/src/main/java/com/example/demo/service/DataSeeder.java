package com.example.demo.service;

import com.example.demo.model.Cinema;
import com.example.demo.model.Hall;
import com.example.demo.repository.CinemaRepository;
import com.example.demo.repository.HallRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CinemaRepository cinemaRepository;
    private final HallRepository hallRepository;

    // Injecting both repositories to ensure the foreign key relationship is saved correctly
    public DataSeeder(CinemaRepository cinemaRepository, HallRepository hallRepository) {
        this.cinemaRepository = cinemaRepository;
        this.hallRepository = hallRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if database is empty before running
        if (cinemaRepository.count() == 0) {
            System.out.println("🌱 AWS Database is empty. Seeding all Egyptian Cinemas and Halls...");

            // --- Cairo & Giza ---
            seedCinemaAndHalls("VOX Cinemas", "Mall of Egypt, Giza", 21);
            seedCinemaAndHalls("VOX Cinemas", "City Centre Almaza, Heliopolis", 16);
            seedCinemaAndHalls("Galaxy Cinema", "Cairo Festival City, New Cairo", 9);
            seedCinemaAndHalls("Galaxy Cinema", "El Manyal, Cairo", 6);
            seedCinemaAndHalls("Galaxy Cinema", "Osman Towers, Maadi", 2);
            seedCinemaAndHalls("Point 90 Cinema", "Fifth Settlement, New Cairo", 10);
            seedCinemaAndHalls("Sun City Cinema", "Sun City Mall, Heliopolis", 12);
            seedCinemaAndHalls("IMAX Plaza", "Arkan Plaza, Sheikh Zayed", 1);
            seedCinemaAndHalls("Renaissance", "Dandy Mega Mall, Giza", 8);
            seedCinemaAndHalls("Sea Cinema", "Arabella Plaza, New Cairo", 5);
            seedCinemaAndHalls("Zawya Cinema", "Downtown Cairo", 2);
            seedCinemaAndHalls("Amir Cinema", "Shoubra, Cairo", 3);

            // --- Alexandria ---
            seedCinemaAndHalls("VOX Cinemas", "City Centre Alexandria", 7);
            seedCinemaAndHalls("Renaissance", "Smouha, Alexandria", 5);
            seedCinemaAndHalls("Renaissance", "San Stefano, Alexandria", 3);
            seedCinemaAndHalls("Cineplex", "Green Plaza, Alexandria", 6);
            seedCinemaAndHalls("Amir Cinema", "Mahatta El Raml, Alexandria", 4);

            // --- Other Governorates ---
            seedCinemaAndHalls("Sea Cinema", "El Gouna, Red Sea", 3);
            seedCinemaAndHalls("Renaissance", "Masria Plaza, Zagazig", 4);
            seedCinemaAndHalls("Donia Cinema", "Ismailia", 2);
            seedCinemaAndHalls("Porto Cinema", "Sokhna", 3);

            System.out.println("✅ AWS Seeding complete! All cinemas and linked halls added.");
        } else {
            System.out.println("📊 AWS Database already has data. Skipping seeding.");
        }
    }

    /**
     * Helper method to create a Cinema, save it, and then generate its Halls.
     * This guarantees the One-To-Many relationship is correctly mapped in SQL Server.
     */
    private void seedCinemaAndHalls(String name, String location, int numHalls) {
        // 1. Create and save the Cinema first
        Cinema cinema = new Cinema();
        cinema.setName(name);
        cinema.setLocation(location);

        // Saving immediately generates the ID in SQL Server needed for the Halls
        Cinema savedCinema = cinemaRepository.save(cinema);

        // 2. Generate the Halls linked to this specific Cinema
        List<Hall> halls = new ArrayList<>();
        for (int i = 1; i <= numHalls; i++) {
            Hall hall = new Hall();
            hall.setName("Hall " + i);
            hall.setTotalSeats(80); // Default average seats
            hall.setCinema(savedCinema); // 🔗 THIS IS THE LINK: Sets the foreign key
            halls.add(hall);
        }

        // 3. Save all halls to the database at once for performance
        hallRepository.saveAll(halls);
    }
}