package com.mbcdev.folkets;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Model to convert a word from a database cursor
 *
 * Created by barry on 21/08/2016.
 */
class Word implements Serializable {

    private final String word;
    private final String comment;
    private final List<WordType> wordTypes;
    private final WordsWithComments translations;
    private final List<String> inflections;
    private final ValuesWithTranslations examples;
    private final ValueWithTranslation definition;
    private final ValueWithTranslation explanation;
    private final String phonetic;
    private final List<String> synonyms;
    private final SaldoLinks saldoLinks;
    private final List<String> compareWith;
    private final ValuesWithTranslations antonyms;
    private final String usage;
    private final String variant;
    private final ValuesWithTranslations idioms;
    private final ValuesWithTranslations derivations;
    private final ValuesWithTranslations compounds;

    /**
     * Creates an instance from the given cursor
     *
     * @param cursor the cursor containing the words.
     */
    Word(@NonNull Context context, @NonNull Cursor cursor) {
        word = cursor.getString(cursor.getColumnIndex("word"));
        comment = cursor.getString(cursor.getColumnIndex("comment"));
        wordTypes = compileWordTypes(cursor.getString(cursor.getColumnIndex("types")));
        translations = new WordsWithComments(cursor.getString(cursor.getColumnIndex("translations")));
        inflections = stringToList(cursor.getString(cursor.getColumnIndex("inflections")));
        examples = new ValuesWithTranslations(cursor.getString(cursor.getColumnIndex("examples")));
        definition = new ValueWithTranslation(cursor.getString(cursor.getColumnIndex("definition")));

        String rawExplanation = cursor.getString(cursor.getColumnIndex("explanation"));
        if (rawExplanation.length() != 0) {
            this.explanation = new ValueWithTranslation(rawExplanation);
        } else {
            this.explanation = null;
        }

        phonetic = cursor.getString(cursor.getColumnIndex("phonetic"));
        synonyms = stringToList(cursor.getString(cursor.getColumnIndex("synonyms")));
        saldoLinks = new SaldoLinks(context, cursor.getString(cursor.getColumnIndex("saldos")));
        compareWith = stringToList(cursor.getString(cursor.getColumnIndex("comparisons")));
        antonyms = new ValuesWithTranslations(cursor.getString(cursor.getColumnIndex("antonyms")));
        usage = cursor.getString(cursor.getColumnIndex("use"));
        variant = cursor.getString(cursor.getColumnIndex("variant"));
        idioms = new ValuesWithTranslations(cursor.getString(cursor.getColumnIndex("idioms")));
        derivations = new ValuesWithTranslations(cursor.getString(cursor.getColumnIndex("derivations")));
        compounds = new ValuesWithTranslations(cursor.getString(cursor.getColumnIndex("compounds")));
    }

    @NonNull
    private List<WordType> compileWordTypes(@NonNull String types) {

        List<WordType> wordTypes = new ArrayList<>();

        for (String type : types.split(",")) {
            wordTypes.add(WordType.lookup(type));
        }

        return wordTypes;
    }

    /**
     * Returns the word, like 'barn'
     *
     * @return the word
     */
    String getWord() {
        return word;
    }

    /**
     * Gets the translations for the word, like 'child'
     *
     * @return the translations for the word
     */
    WordsWithComments getTranslations() {
        return translations;
    }

    /**
     * Gets the comment for the word
     *
     * @return the comment for the word
     */
    String getComment() {
        return comment;
    }

    /**
     * Gets a list of types of this word, like noun, adjective
     *
     * @return a list of types of this word
     */
    List<WordType> getWordTypes() {
        return wordTypes;
    }

    /**
     * Gets a list of the inflected values of this word
     *
     * @return a list of the inflected values of this word
     */
    List<String> getInflections() {
        return inflections;
    }

    /**
     * Gets the example usages of this word
     *
     * @return the example usages of this word
     */
    ValuesWithTranslations getExamples() {
        return examples;
    }

    /**
     * Gets the definition of this word
     *
     * @return the definition of this word
     */
    ValueWithTranslation getDefinition() {
        return definition;
    }

    /**
     * Gets the explanation of this word
     *
     * @return the explanation of this word
     */
    ValueWithTranslation getExplanation() {
        return explanation;
    }

    /**
     * Gets the phonetic of this word
     *
     * @return the phonetic of this word
     */
    String getPhonetic() {
        return phonetic;
    }

    /**
     * Gets this word's synonyms
     *
     * @return this word's synonyms
     */
    List<String> getSynonyms() {
        return synonyms;
    }

    /**
     * Gets this word's links to Saldo
     *
     * @return this word's links to Saldo
     */
    SaldoLinks getSaldoLinks() {
        return saldoLinks;
    }

    /**
     * Gets a list of words to compare with this word
     *
     * @return a list of words to compare with this word
     */
    List<String> getCompareWith() {
        return compareWith;
    }

    /**
     * Gets a list of antonyms of this word
     *
     * @return a list of antonyms of this word
     */
    ValuesWithTranslations getAntonyms() {
        return antonyms;
    }

    /**
     * Gets the usage of this word
     *
     * @return the usage of this word
     */
    String getUsage() {
        return usage;
    }

    /**
     * Gets the variant of this word
     *
     * @return the variant of this word
     */
    String getVariant() {
        return variant;
    }

    /**
     * Gets the idioms of this word
     *
     * @return the idioms of this word
     */
    ValuesWithTranslations getIdioms() {
        return idioms;
    }

    /**
     * Gets the derivations of this word
     *
     * @return the derivations of this word
     */
    ValuesWithTranslations getDerivations() {
        return derivations;
    }

    /**
     * Gets a list of words that feature this word as a part of a compound word
     *
     * @return a list of words that feature this word as a part of a compound word
     */
    ValuesWithTranslations getCompounds() {
        return compounds;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", comment='" + comment + '\'' +
                ", wordTypes=" + wordTypes +
                ", translations=" + translations +
                ", inflections=" + inflections +
                ", examples=" + examples +
                ", definition=" + definition +
                ", explanation=" + explanation +
                ", phonetic='" + phonetic + '\'' +
                ", synonyms=" + synonyms +
                ", saldoLinks=" + saldoLinks +
                ", compareWith=" + compareWith +
                ", antonyms=" + antonyms +
                ", usage='" + usage + '\'' +
                ", variant='" + variant + '\'' +
                ", idioms=" + idioms +
                ", derivations=" + derivations +
                ", compounds=" + compounds +
                '}';
    }

    /**
     * Converts a csv string to a list
     *
     * @param csvString a csv string
     * @return A list of Strings, or an empty list
     */
    @NonNull
    private List<String> stringToList(@Nullable String csvString) {

        List<String> strings = new ArrayList<>();

        if (Utils.hasLength(csvString)) {
            strings = Arrays.asList(csvString.split(Utils.ASTERISK_SEPARATOR));
        }

        return strings;
    }

}
