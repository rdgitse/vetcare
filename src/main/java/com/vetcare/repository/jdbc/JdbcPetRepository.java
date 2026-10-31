// package com.vetcare.repository.jdbc;

// public class JdbcPetRepository implements PetRepository {
//     private static final Logger LOGGER = Logger.getLogger(JdbcPetRepository.class.getName());
//     private final Connection connection;

//     public JdbcPetRepository(Connection connection) { this.connection = connection; }

//     @Override
//     public Pet save(Pet pet) {
//         String sql = "INSERT INTO pets (owner_id, name, species, breed, birth_date) "
//                    + "VALUES (?, ?, ?, ?, ?) RETURNING id";
//         try (PreparedStatement ps = connection.prepareStatement(sql)) {
//             ps.setLong(1, pet.getOwnerId());
//             ps.setString(2, pet.getName());
//             ps.setString(3, pet.getSpecies().name());              // enum → texto
//             ps.setString(4, pet.getBreed());
//             ps.setObject(5, pet.getBirthDate());                    // LocalDate → DATE
//             try (ResultSet rs = ps.executeQuery()) {
//                 rs.next();
//                 pet.setId(rs.getLong("id"));
//                 return pet;
//             }
//         } catch (SQLException e) {
//             LOGGER.log(Level.SEVERE, "Error al guardar la mascota " + pet.getName(), e);
//             throw new DataAccessException("No se pudo guardar la mascota", e);
//         }
//     }

//     @Override
//     public List<Pet> findByOwner(long ownerId) {
//         String sql = "SELECT * FROM pets WHERE owner_id = ? ORDER BY name";
//         try (PreparedStatement ps = connection.prepareStatement(sql)) {
//             ps.setLong(1, ownerId);
//             List<Pet> result = new ArrayList<>();
//             try (ResultSet rs = ps.executeQuery()) {
//                 while (rs.next()) result.add(mapRow(rs));
//             }
//             return result;
//         } catch (SQLException e) {
//             LOGGER.log(Level.SEVERE, "Error al listar mascotas del dueño " + ownerId, e);
//             throw new DataAccessException("No se pudo consultar las mascotas", e);
//         }
//     }

//     // findById, update y deleteById siguen el mismo molde

//     private Pet mapRow(ResultSet rs) throws SQLException {
//         return new Pet(rs.getLong("id"), rs.getLong("owner_id"), rs.getString("name"),
//                 Species.valueOf(rs.getString("species")), rs.getString("breed"),
//                 rs.getObject("birth_date", LocalDate.class));
//     }
// }

