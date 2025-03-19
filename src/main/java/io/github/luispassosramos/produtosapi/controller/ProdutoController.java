package io.github.luispassosramos.produtosapi.controller;

import io.github.luispassosramos.produtosapi.model.Produto;
import io.github.luispassosramos.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto){
        System.out.println("Produto recebido:" + produto);

        var id = UUID.randomUUID().toString();
        produto.setId(id);

        produtoRepository.save(produto);
        return produto;
    }

    @PostMapping("salvarLista")
    public List<Produto> salvarLista(@RequestBody List<Produto> produto){

        System.out.println("Produto recebido:" + produto);

        for (Produto produto1 : produto){
            var id = UUID.randomUUID().toString();
            produto1.setId(id);
        }


        produtoRepository.saveAll(produto);
        return produto;
    }

    @GetMapping("{id}")
    public Produto obterPorId(@PathVariable("id") String id){
        return produtoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("{id}")
    public void deletarPorId(@PathVariable("id") String id){
        produtoRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public void atualizarPorId(@PathVariable("id") String id, @RequestBody Produto produto){
        produto.setId(id);
        produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> busca(@RequestParam("nome") String nome){
        return produtoRepository.findByNome(nome);
    }
}
