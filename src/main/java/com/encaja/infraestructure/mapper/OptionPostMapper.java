package com.encaja.infraestructure.mapper;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class OptionPostMapper {
/*
    OptionPostDto OptionPostEntityToOptionPostDto(OptionPost optionPost,String userId) {
        return OptionPostDto.builder()
                .text(optionPost.getText())
                .urlMedia(optionPost.getUrlMedia())
                .postid(optionPost.getOptionPostPK().getPostid())
                .number(optionPost.getOptionPostPK().getNumber())
                .votes(optionPost.getReactionList().size())
                .myReactions(
                        optionPost.getReactionList().stream()
                                .filter(reaction -> reaction.getReactionPK().getWho().equals(userId))
                                .toList()
                )
                .correctOption(optionPost.getCorrectOption())
                .build();
    }

    List<OptionPostDto> ListOptionPostEntityToListOptionPostDto(List<OptionPost> optionPostList,String userId) {
        return optionPostList.stream()
                .map(optionPost -> OptionPostEntityToOptionPostDto(optionPost,userId))
                .collect(Collectors.toList());
    }

    private List<Reaction> findReactionsByUser(List<Reaction> reactions,String userId){
        reactions.stream().filter(reaction -> )

    }*/

}
