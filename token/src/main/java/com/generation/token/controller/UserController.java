package com.generation.token.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.token.model.Produto;
import com.generation.token.model.Token;
import com.generation.token.model.Usuario;
import com.generation.token.util.Authenticator;

@RestController
@CrossOrigin("*")
public class UserController {
	
		@PostMapping("/autentificacao")
		public ResponseEntity<Token> autenticate(@RequestBody Usuario usuario) {
			System.out.println(usuario.getUsername());
			System.out.println(usuario.getPassword());
			if (usuario.getUsername().equals("user") && usuario.getPassword().equals("1234")) {
				System.out.println(Authenticator.encode(usuario));
				return ResponseEntity.ok(new Token(Authenticator.encode(usuario)));
			}
			return ResponseEntity.status(403).build();
		}

		@GetMapping("/produtos/todos")
		public ResponseEntity<ArrayList<Produto>> getAlunos(@RequestParam String token) {
			try {
				if (Authenticator.isValid(token)) {
					ArrayList<Produto> lista = new ArrayList<>();
					for (int i = 1; i <= 10; i++) {
						lista.add(new Produto(i, "nome" + i, "marca" + i, "descrição" + i));
					}
					return ResponseEntity.ok(lista);
				}
				return ResponseEntity.status(403).build();
			} catch (Exception ex) {
				return ResponseEntity.status(400).build();
			}
		}

}
