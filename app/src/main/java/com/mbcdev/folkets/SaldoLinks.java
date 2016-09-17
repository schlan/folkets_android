package com.mbcdev.folkets;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Models a collection of {@link SaldoLink}
 *
 * Created by barry on 21/08/2016.
 */
public class SaldoLinks implements Parcelable {

    private final List<SaldoLink> links;

    /**
     * Creates an instance from the raw database value
     *
     * @param rawValue the raw database value
     */
    public SaldoLinks(String rawValue) {

        String[] rawLinks = rawValue.split(Utils.ASTERISK_SEPARATOR);
        links = new ArrayList<>();

        for (String rawLink : rawLinks) {
            links.add(new SaldoLink(rawLink));
        }
    }

    /**
     * Gets the saldo links
     *
     * @return the saldo links
     */
    public List<SaldoLink> getLinks() {

        if (links.size() > 0) {
            System.out.println();
        }

        return links;
    }

    @Override
    public String toString() {
        return "SaldoLinks{" +
                "links=" + links +
                '}';
    }

    protected SaldoLinks(Parcel in) {
        if (in.readByte() == 0x01) {
            links = new ArrayList<>();
            in.readList(links, SaldoLink.class.getClassLoader());
        } else {
            links = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (links == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(links);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SaldoLinks> CREATOR = new Parcelable.Creator<SaldoLinks>() {
        @Override
        public SaldoLinks createFromParcel(Parcel in) {
            return new SaldoLinks(in);
        }

        @Override
        public SaldoLinks[] newArray(int size) {
            return new SaldoLinks[size];
        }
    };
}