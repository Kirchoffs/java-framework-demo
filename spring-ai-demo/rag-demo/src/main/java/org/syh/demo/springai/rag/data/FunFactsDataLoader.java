package org.syh.demo.springai.rag.data;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class FunFactsDataLoader {
    private final VectorStore vectorStore;

    public FunFactsDataLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    private void loadSentenceIntoVectorStore() {
        List<String> funFacts = List.of(
            "A cloud weighs around a million tonnes.",
            "Giraffes are 30 times more likely to get hit by lightning than people.",
            "Identical twins don't have the same fingerprints.",
            "Earth's rotation is changing speed.",
            "Earlobes have no biological purpose.",
            "Your brain is constantly eating itself.",
            "The largest piece of fossilised dinosaur poo discovered is over 30cm long and over two litres in volume.",
            "Mars isn't actually round.",
            "There's no such thing as zero-calorie foods.",
            "The Universe's average colour is called 'Cosmic latte'.",
            "Animals can experience time differently from humans.",
            "Water might not be wet.",
            "Most people stroke cats the wrong way.",
            "A chicken once lived for 18 months without a head.",
            "The raw ingredients of a human body would cost over £116,000.",
            "All the world's bacteria stacked on top of each other would stretch for 10 billion light-years.",
            "Wearing a tie can reduce blood flow to the brain by 7.5 per cent.",
            "The fear of long words is called Hippopotomonstrosesquippedaliophobia.",
            "The world's oldest dog lived to 29.5 years old.",
            "The world's oldest cat lived to 38 years and three days old.",
            "The Sun makes a sound but we can't hear it.",
            "Mount Everest isn't the tallest mountain on Earth.",
            "Our solar system has a wall.",
            "Octopuses don't actually have tentacles.",
            "Most maps of the world are wrong.",
            "NASA genuinely faked part of the Moon landing.",
            "Comets smell like rotten eggs.",
            "Earth's poles are moving.",
            "You can actually die laughing.",
            "Chainsaws were first invented for childbirth.",
            "Ants don't have lungs.",
            "You don't actually lose most of your heat through your head.",
            "The T.rex likely had feathers.",
            "Football teams wearing red kits play better.",
            "When you cut a worm in two, it regenerates.",
            "Wind turbines kill between 10,000 and 100,000 birds each year in the UK.",
            "Snails have teeth.",
            "Sound can be minus decibels.",
            "A horse normally has more than one horsepower.",
            "Your signature could reveal personality traits.",
            "One in 18 people have a third nipple.",
            "Bananas are radioactive."
        );

        List<Document> documents = funFacts.stream().map(Document::new).collect(Collectors.toList());
        vectorStore.add(documents);
    }
}
