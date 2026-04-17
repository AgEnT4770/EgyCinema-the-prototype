const MOVIES = [
  { id:"dune-2", title:"Dune: Part Two",
    poster:"https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg",
    backdrop:"https://image.tmdb.org/t/p/original/8b8R8l88Qje9dn9OE8PY05Nxl1X.jpg",
    genre:"Sci-Fi · Adventure", duration:"2h 46m", rating:"PG-13", year:2024,
    description:"Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the known universe, he endeavors to prevent a terrible future only he can foresee." },
  { id:"oppenheimer", title:"Oppenheimer",
    poster:"https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg",
    backdrop:"https://image.tmdb.org/t/p/original/fm6KqXpk3M2HVveHwCrBSSBaO0V.jpg",
    genre:"Biography · Drama", duration:"3h 0m", rating:"R", year:2023,
    description:"The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb during World War II." },
  { id:"Family-Business", title:"Family Business",
    poster:"https://www.themoviedb.org/t/p/w1280/cTkgBDGGDPlUzLJ1zEWmOopoLdV.jpg",
    backdrop:"https://media.themoviedb.org/t/p/w1066_and_h600_face/aFh0O1steZzPY2pZ5PhjrbGCIoE.jpg",
    genre:"Drama and Comedy", duration:"2h 25m", rating:"PG-13", year:2026,
    description:"A poor family struggles to survive through petty theft, but the father decides to abandon crime after an incident that nearly lands him in prison. He devises a different plan: each family member gets a job working for a wealthy family, concealing their familial ties. They constantly confront the stark contrast between their own world and that of their employers." },
  { 
    id: "Scream7-2026", title: "Scream 7",
    poster: "https://www.themoviedb.org/t/p/w1280/jjyuk0edLiW8vOSnlfwWCCLpbh5.jpg",
    backdrop: "https://media.themoviedb.org/t/p/w1066_and_h600_face/t9J2HXaDuJR7bvIG9XF7mttn4VY.jpg",
    genre: "Horror", duration: "1h 54m", rating: "PG-13", year: 2026,
    description: "When a new Ghostface killer emerges in the quiet town where Sidney Prescott has built a new life, her darkest fears are realized as her daughter becomes the next target. Determined to protect her family, Sidney must face the horrors of her past to put an end to the bloodshed once and for all."
  },{ id:"deadpool-3", title:"Deadpool & Wolverine",
    poster:"https://image.tmdb.org/t/p/w500/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg",
    backdrop:"https://image.tmdb.org/t/p/original/yDHYTfA3R0jFYba16jBB1ef8oIt.jpg",
    genre:"Action · Comedy", duration:"2h 7m", rating:"R", year:2024,
    description:"A listless Wade Wilson toils away in civilian life. When his homeworld faces an existential threat, Wade must reluctantly suit-up again with an even more reluctant Wolverine." },
  { id:"inside-out-2", title:"Inside Out 2",
    poster:"https://image.tmdb.org/t/p/w500/vpnVM9B6NMmQpWeZvzLvDESb2QY.jpg",
    backdrop:"https://image.tmdb.org/t/p/original/stKGOm8UyhuLPR9sZLjs5AkmncA.jpg",
    genre:"Animation · Family", duration:"1h 36m", rating:"PG", year:2024,
    description:"Teenager Riley's mind headquarters is undergoing a sudden demolition to make room for something entirely unexpected: new emotions!" },
  { id:"gladiator-2", title:"Gladiator II",
    poster:"https://image.tmdb.org/t/p/w500/2cxhvwyEwRlysAmRH4iodkvo0z5.jpg",
    backdrop:"https://image.tmdb.org/t/p/original/euYIwmwkmz95mnXvufEmbL6ovhZ.jpg",
    genre:"Action · Drama", duration:"2h 28m", rating:"R", year:2024,
    description:"After his home is conquered by the tyrannical emperors who now lead Rome, Lucius is forced to enter the Colosseum and must look to his past to find strength." },
  { id:"wicked", title:"Wicked",
    poster:"https://image.tmdb.org/t/p/w500/c5Tqxeo1UpBvnAc3csUm7j3hlQl.jpg",
    backdrop:"https://image.tmdb.org/t/p/original/uVlUu174iiKhsTbQPvLxIxbK5wb.jpg",
    genre:"Musical · Fantasy", duration:"2h 40m", rating:"PG", year:2024,
    description:"Elphaba, a misunderstood young woman because of her green skin, and Glinda, a popular girl, become friends at Shiz University in the Land of Oz." },
];

const CINEMAS = [
  { id:"vox-mall-of-egypt", name:"VOX Cinemas — Mall of Egypt", location:"6th of October, Giza",
    movieIds:["dune-2","deadpool-3","wicked","gladiator-2","inside-out-2"] },
  { id:"imax-citystars", name:"IMAX — Citystars", location:"Heliopolis, Cairo",
    movieIds:["oppenheimer","Family-Business","dune-2","Scream7-2026"] },
  { id:"renaissance-nile-city", name:"Renaissance — Nile City", location:"Corniche El Nil, Cairo",
    movieIds:["Scream7-2026","wicked","gladiator-2","inside-out-2","deadpool-3"] },
  { id:"zawya-downtown", name:"Zawya — Downtown", location:"Emad El Din, Cairo",
    movieIds:["oppenheimer","Family-Business","dune-2"] },
];

const getMovie = id => MOVIES.find(m => m.id === id);
const getCinema = id => CINEMAS.find(c => c.id === id);
const getCinemasForMovie = (id) => CINEMAS.filter(c => c.movieIds.includes(id));
