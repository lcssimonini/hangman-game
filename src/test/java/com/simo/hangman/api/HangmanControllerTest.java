package com.simo.hangman.api;

import static com.simo.hangman.domain.GameResult.ONGOING;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class HangmanControllerTest {

  private static final String GAME_PATH = "/game";

  @Autowired private MockMvc mvc;

  @Test
  public void shouldCreateGame() throws Exception {
    mvc.perform(post(GAME_PATH).contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.gameResult", is(ONGOING.toString())))
        .andExpect(jsonPath("$.hits", is(0)))
        .andExpect(jsonPath("$.wrongGuessesCount", is(0)))
        .andExpect(jsonPath("$.guesses", is(empty())));
  }
}
