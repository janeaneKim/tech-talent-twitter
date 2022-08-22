package com.tts.techtalenttwitter2.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetDisplay {
//holds form og the tweet for display
//difference between tweet= createdAt is String	
	private User user;
	private String message;
	private List<Tag> tags;
	private String date;

}
