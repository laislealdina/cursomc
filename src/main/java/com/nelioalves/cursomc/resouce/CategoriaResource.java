package com.nelioalves.cursomc.resouce;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/Categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDTO){		
		Categoria categoria = service.fromDTO(categoriaDTO);
		categoria = service.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id){
		categoriaDTO.setId(id);
		Categoria categoria = service.fromDTO(categoriaDTO);
		categoria = service.update(categoria);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
	    service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<Categoria> list_categoria = new ArrayList<>();
		list_categoria = service.findAll();
		List<CategoriaDTO> list_categoria_dto = new ArrayList<>();
		list_categoria_dto = list_categoria.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list_categoria_dto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value="page", defaultValue="0") Integer page,
													   @RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
													   @RequestParam (value="orderBy", defaultValue="nome")String orderBy,
													   @RequestParam(value="direction", defaultValue="ASC")String direction){
		
		Page<Categoria> page_categoria = service.findPage(page,linesPerPage,orderBy,direction);
		Page<CategoriaDTO> page_categoriaDTO = page_categoria.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(page_categoriaDTO);
		
	}
	
	
}
