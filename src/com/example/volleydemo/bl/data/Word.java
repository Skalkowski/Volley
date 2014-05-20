package com.example.volleydemo.bl.data;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Word {
	public long id;
	@SerializedName("namelanguage1")
	public String nameLanguage1;
	@SerializedName("namelanguage2")
	public String nameLanguage2;
	public ArrayList<Tag> tags;
	//TODO: Reszte zrób se sam
}