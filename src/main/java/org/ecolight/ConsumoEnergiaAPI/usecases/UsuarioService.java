package org.ecolight.ConsumoEnergiaAPI.usecases;

import jakarta.transaction.Transactional;
import org.ecolight.ConsumoEnergiaAPI.domains.Usuario;
import org.ecolight.ConsumoEnergiaAPI.gateways.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Page<Usuario> listarUsuariosComPaginacao(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Transactional
    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Optional<Usuario> atualizarUsuario(Integer id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id).map(usuarioExistente -> {
            usuarioExistente.setNome(usuarioAtualizado.getNome());
            usuarioExistente.setEmail(usuarioAtualizado.getEmail());
            usuarioExistente.setSenha(usuarioAtualizado.getSenha());
            usuarioExistente.setTelefone(usuarioAtualizado.getTelefone());
            usuarioExistente.setGenero(usuarioAtualizado.getGenero());
            return usuarioRepository.save(usuarioExistente);
        });
    }

    @Transactional
    public boolean excluirUsuario(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
