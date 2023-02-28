package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Empleado;
import Models.Inventariodetalleacciones;
import java.util.ArrayList;
import java.util.List;
import Models.Compra;
import Models.Venta;
import Models.Abono;
import Models.Gasto;
import Models.Cotizacion;
import Models.Solicitud;
import Models.Arqueo;
import Models.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getInventariodetalleaccionesList() == null) {
            usuario.setInventariodetalleaccionesList(new ArrayList<>());
        }
        if (usuario.getCompraList() == null) {
            usuario.setCompraList(new ArrayList<>());
        }
        if (usuario.getVentaList() == null) {
            usuario.setVentaList(new ArrayList<>());
        }
        if (usuario.getAbonoList() == null) {
            usuario.setAbonoList(new ArrayList<>());
        }
        if (usuario.getGastoList() == null) {
            usuario.setGastoList(new ArrayList<>());
        }
        if (usuario.getCotizacionList() == null) {
            usuario.setCotizacionList(new ArrayList<>());
        }
        if (usuario.getSolicitudList() == null) {
            usuario.setSolicitudList(new ArrayList<>());
        }
        if (usuario.getArqueoList() == null) {
            usuario.setArqueoList(new ArrayList<>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleadoID = usuario.getEmpleadoID();
            if (empleadoID != null) {
                empleadoID = em.getReference(empleadoID.getClass(), empleadoID.getEmpleadoID());
                usuario.setEmpleadoID(empleadoID);
            }
            List<Inventariodetalleacciones> attachedInventariodetalleaccionesList = new ArrayList<>();
            for (Inventariodetalleacciones inventariodetalleaccionesListInventariodetalleaccionesToAttach : usuario.getInventariodetalleaccionesList()) {
                inventariodetalleaccionesListInventariodetalleaccionesToAttach = em.getReference(inventariodetalleaccionesListInventariodetalleaccionesToAttach.getClass(), inventariodetalleaccionesListInventariodetalleaccionesToAttach.getInventarioDetalleAccionesID());
                attachedInventariodetalleaccionesList.add(inventariodetalleaccionesListInventariodetalleaccionesToAttach);
            }
            usuario.setInventariodetalleaccionesList(attachedInventariodetalleaccionesList);
            List<Compra> attachedCompraList = new ArrayList<>();
            for (Compra compraListCompraToAttach : usuario.getCompraList()) {
                compraListCompraToAttach = em.getReference(compraListCompraToAttach.getClass(), compraListCompraToAttach.getCompraID());
                attachedCompraList.add(compraListCompraToAttach);
            }
            usuario.setCompraList(attachedCompraList);
            List<Venta> attachedVentaList = new ArrayList<>();
            for (Venta ventaListVentaToAttach : usuario.getVentaList()) {
                ventaListVentaToAttach = em.getReference(ventaListVentaToAttach.getClass(), ventaListVentaToAttach.getVentaID());
                attachedVentaList.add(ventaListVentaToAttach);
            }
            usuario.setVentaList(attachedVentaList);
            List<Abono> attachedAbonoList = new ArrayList<>();
            for (Abono abonoListAbonoToAttach : usuario.getAbonoList()) {
                abonoListAbonoToAttach = em.getReference(abonoListAbonoToAttach.getClass(), abonoListAbonoToAttach.getAbonoID());
                attachedAbonoList.add(abonoListAbonoToAttach);
            }
            usuario.setAbonoList(attachedAbonoList);
            List<Gasto> attachedGastoList = new ArrayList<>();
            for (Gasto gastoListGastoToAttach : usuario.getGastoList()) {
                gastoListGastoToAttach = em.getReference(gastoListGastoToAttach.getClass(), gastoListGastoToAttach.getGastoID());
                attachedGastoList.add(gastoListGastoToAttach);
            }
            usuario.setGastoList(attachedGastoList);
            List<Cotizacion> attachedCotizacionList = new ArrayList<>();
            for (Cotizacion cotizacionListCotizacionToAttach : usuario.getCotizacionList()) {
                cotizacionListCotizacionToAttach = em.getReference(cotizacionListCotizacionToAttach.getClass(), cotizacionListCotizacionToAttach.getCotizacionID());
                attachedCotizacionList.add(cotizacionListCotizacionToAttach);
            }
            usuario.setCotizacionList(attachedCotizacionList);
            List<Solicitud> attachedSolicitudList = new ArrayList<>();
            for (Solicitud solicitudListSolicitudToAttach : usuario.getSolicitudList()) {
                solicitudListSolicitudToAttach = em.getReference(solicitudListSolicitudToAttach.getClass(), solicitudListSolicitudToAttach.getSolicitudID());
                attachedSolicitudList.add(solicitudListSolicitudToAttach);
            }
            usuario.setSolicitudList(attachedSolicitudList);
            List<Arqueo> attachedArqueoList = new ArrayList<>();
            for (Arqueo arqueoListArqueoToAttach : usuario.getArqueoList()) {
                arqueoListArqueoToAttach = em.getReference(arqueoListArqueoToAttach.getClass(), arqueoListArqueoToAttach.getArqueoID());
                attachedArqueoList.add(arqueoListArqueoToAttach);
            }
            usuario.setArqueoList(attachedArqueoList);
            em.persist(usuario);
            if (empleadoID != null) {
                empleadoID.getUsuarioList().add(usuario);
                empleadoID = em.merge(empleadoID);
            }
            for (Inventariodetalleacciones inventariodetalleaccionesListInventariodetalleacciones : usuario.getInventariodetalleaccionesList()) {
                Usuario oldUsuarioIDOfInventariodetalleaccionesListInventariodetalleacciones = inventariodetalleaccionesListInventariodetalleacciones.getUsuarioID();
                inventariodetalleaccionesListInventariodetalleacciones.setUsuarioID(usuario);
                inventariodetalleaccionesListInventariodetalleacciones = em.merge(inventariodetalleaccionesListInventariodetalleacciones);
                if (oldUsuarioIDOfInventariodetalleaccionesListInventariodetalleacciones != null) {
                    oldUsuarioIDOfInventariodetalleaccionesListInventariodetalleacciones.getInventariodetalleaccionesList().remove(inventariodetalleaccionesListInventariodetalleacciones);
                    oldUsuarioIDOfInventariodetalleaccionesListInventariodetalleacciones = em.merge(oldUsuarioIDOfInventariodetalleaccionesListInventariodetalleacciones);
                }
            }
            for (Compra compraListCompra : usuario.getCompraList()) {
                Usuario oldUsuarioIDOfCompraListCompra = compraListCompra.getUsuarioID();
                compraListCompra.setUsuarioID(usuario);
                compraListCompra = em.merge(compraListCompra);
                if (oldUsuarioIDOfCompraListCompra != null) {
                    oldUsuarioIDOfCompraListCompra.getCompraList().remove(compraListCompra);
                    oldUsuarioIDOfCompraListCompra = em.merge(oldUsuarioIDOfCompraListCompra);
                }
            }
            for (Venta ventaListVenta : usuario.getVentaList()) {
                Usuario oldUsuarioIDOfVentaListVenta = ventaListVenta.getUsuarioID();
                ventaListVenta.setUsuarioID(usuario);
                ventaListVenta = em.merge(ventaListVenta);
                if (oldUsuarioIDOfVentaListVenta != null) {
                    oldUsuarioIDOfVentaListVenta.getVentaList().remove(ventaListVenta);
                    oldUsuarioIDOfVentaListVenta = em.merge(oldUsuarioIDOfVentaListVenta);
                }
            }
            for (Abono abonoListAbono : usuario.getAbonoList()) {
                Usuario oldUsuarioIDOfAbonoListAbono = abonoListAbono.getUsuarioID();
                abonoListAbono.setUsuarioID(usuario);
                abonoListAbono = em.merge(abonoListAbono);
                if (oldUsuarioIDOfAbonoListAbono != null) {
                    oldUsuarioIDOfAbonoListAbono.getAbonoList().remove(abonoListAbono);
                    oldUsuarioIDOfAbonoListAbono = em.merge(oldUsuarioIDOfAbonoListAbono);
                }
            }
            for (Gasto gastoListGasto : usuario.getGastoList()) {
                Usuario oldUsuarioIDOfGastoListGasto = gastoListGasto.getUsuarioID();
                gastoListGasto.setUsuarioID(usuario);
                gastoListGasto = em.merge(gastoListGasto);
                if (oldUsuarioIDOfGastoListGasto != null) {
                    oldUsuarioIDOfGastoListGasto.getGastoList().remove(gastoListGasto);
                    oldUsuarioIDOfGastoListGasto = em.merge(oldUsuarioIDOfGastoListGasto);
                }
            }
            for (Cotizacion cotizacionListCotizacion : usuario.getCotizacionList()) {
                Usuario oldUsuarioIDOfCotizacionListCotizacion = cotizacionListCotizacion.getUsuarioID();
                cotizacionListCotizacion.setUsuarioID(usuario);
                cotizacionListCotizacion = em.merge(cotizacionListCotizacion);
                if (oldUsuarioIDOfCotizacionListCotizacion != null) {
                    oldUsuarioIDOfCotizacionListCotizacion.getCotizacionList().remove(cotizacionListCotizacion);
                    oldUsuarioIDOfCotizacionListCotizacion = em.merge(oldUsuarioIDOfCotizacionListCotizacion);
                }
            }
            for (Solicitud solicitudListSolicitud : usuario.getSolicitudList()) {
                Usuario oldUsuarioIDOfSolicitudListSolicitud = solicitudListSolicitud.getUsuarioID();
                solicitudListSolicitud.setUsuarioID(usuario);
                solicitudListSolicitud = em.merge(solicitudListSolicitud);
                if (oldUsuarioIDOfSolicitudListSolicitud != null) {
                    oldUsuarioIDOfSolicitudListSolicitud.getSolicitudList().remove(solicitudListSolicitud);
                    oldUsuarioIDOfSolicitudListSolicitud = em.merge(oldUsuarioIDOfSolicitudListSolicitud);
                }
            }
            for (Arqueo arqueoListArqueo : usuario.getArqueoList()) {
                Usuario oldUsuarioIDOfArqueoListArqueo = arqueoListArqueo.getUsuarioID();
                arqueoListArqueo.setUsuarioID(usuario);
                arqueoListArqueo = em.merge(arqueoListArqueo);
                if (oldUsuarioIDOfArqueoListArqueo != null) {
                    oldUsuarioIDOfArqueoListArqueo.getArqueoList().remove(arqueoListArqueo);
                    oldUsuarioIDOfArqueoListArqueo = em.merge(oldUsuarioIDOfArqueoListArqueo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getUsuarioID());
            Empleado empleadoIDOld = persistentUsuario.getEmpleadoID();
            Empleado empleadoIDNew = usuario.getEmpleadoID();
            List<Inventariodetalleacciones> inventariodetalleaccionesListOld = persistentUsuario.getInventariodetalleaccionesList();
            List<Inventariodetalleacciones> inventariodetalleaccionesListNew = usuario.getInventariodetalleaccionesList();
            List<Compra> compraListOld = persistentUsuario.getCompraList();
            List<Compra> compraListNew = usuario.getCompraList();
            List<Venta> ventaListOld = persistentUsuario.getVentaList();
            List<Venta> ventaListNew = usuario.getVentaList();
            List<Abono> abonoListOld = persistentUsuario.getAbonoList();
            List<Abono> abonoListNew = usuario.getAbonoList();
            List<Gasto> gastoListOld = persistentUsuario.getGastoList();
            List<Gasto> gastoListNew = usuario.getGastoList();
            List<Cotizacion> cotizacionListOld = persistentUsuario.getCotizacionList();
            List<Cotizacion> cotizacionListNew = usuario.getCotizacionList();
            List<Solicitud> solicitudListOld = persistentUsuario.getSolicitudList();
            List<Solicitud> solicitudListNew = usuario.getSolicitudList();
            List<Arqueo> arqueoListOld = persistentUsuario.getArqueoList();
            List<Arqueo> arqueoListNew = usuario.getArqueoList();
            List<String> illegalOrphanMessages = null;
            for (Inventariodetalleacciones inventariodetalleaccionesListOldInventariodetalleacciones : inventariodetalleaccionesListOld) {
                if (!inventariodetalleaccionesListNew.contains(inventariodetalleaccionesListOldInventariodetalleacciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<>();
                    }
                    illegalOrphanMessages.add("You must retain Inventariodetalleacciones " + inventariodetalleaccionesListOldInventariodetalleacciones + " since its usuarioID field is not nullable.");
                }
            }
            for (Compra compraListOldCompra : compraListOld) {
                if (!compraListNew.contains(compraListOldCompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<>();
                    }
                    illegalOrphanMessages.add("You must retain Compra " + compraListOldCompra + " since its usuarioID field is not nullable.");
                }
            }
            for (Venta ventaListOldVenta : ventaListOld) {
                if (!ventaListNew.contains(ventaListOldVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<>();
                    }
                    illegalOrphanMessages.add("You must retain Venta " + ventaListOldVenta + " since its usuarioID field is not nullable.");
                }
            }
            for (Abono abonoListOldAbono : abonoListOld) {
                if (!abonoListNew.contains(abonoListOldAbono)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<>();
                    }
                    illegalOrphanMessages.add("You must retain Abono " + abonoListOldAbono + " since its usuarioID field is not nullable.");
                }
            }
            for (Gasto gastoListOldGasto : gastoListOld) {
                if (!gastoListNew.contains(gastoListOldGasto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<>();
                    }
                    illegalOrphanMessages.add("You must retain Gasto " + gastoListOldGasto + " since its usuarioID field is not nullable.");
                }
            }
            for (Cotizacion cotizacionListOldCotizacion : cotizacionListOld) {
                if (!cotizacionListNew.contains(cotizacionListOldCotizacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<>();
                    }
                    illegalOrphanMessages.add("You must retain Cotizacion " + cotizacionListOldCotizacion + " since its usuarioID field is not nullable.");
                }
            }
            for (Solicitud solicitudListOldSolicitud : solicitudListOld) {
                if (!solicitudListNew.contains(solicitudListOldSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitud " + solicitudListOldSolicitud + " since its usuarioID field is not nullable.");
                }
            }
            for (Arqueo arqueoListOldArqueo : arqueoListOld) {
                if (!arqueoListNew.contains(arqueoListOldArqueo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<>();
                    }
                    illegalOrphanMessages.add("You must retain Arqueo " + arqueoListOldArqueo + " since its usuarioID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (empleadoIDNew != null) {
                empleadoIDNew = em.getReference(empleadoIDNew.getClass(), empleadoIDNew.getEmpleadoID());
                usuario.setEmpleadoID(empleadoIDNew);
            }
            List<Inventariodetalleacciones> attachedInventariodetalleaccionesListNew = new ArrayList<>();
            for (Inventariodetalleacciones inventariodetalleaccionesListNewInventariodetalleaccionesToAttach : inventariodetalleaccionesListNew) {
                inventariodetalleaccionesListNewInventariodetalleaccionesToAttach = em.getReference(inventariodetalleaccionesListNewInventariodetalleaccionesToAttach.getClass(), inventariodetalleaccionesListNewInventariodetalleaccionesToAttach.getInventarioDetalleAccionesID());
                attachedInventariodetalleaccionesListNew.add(inventariodetalleaccionesListNewInventariodetalleaccionesToAttach);
            }
            inventariodetalleaccionesListNew = attachedInventariodetalleaccionesListNew;
            usuario.setInventariodetalleaccionesList(inventariodetalleaccionesListNew);
            List<Compra> attachedCompraListNew = new ArrayList<>();
            for (Compra compraListNewCompraToAttach : compraListNew) {
                compraListNewCompraToAttach = em.getReference(compraListNewCompraToAttach.getClass(), compraListNewCompraToAttach.getCompraID());
                attachedCompraListNew.add(compraListNewCompraToAttach);
            }
            compraListNew = attachedCompraListNew;
            usuario.setCompraList(compraListNew);
            List<Venta> attachedVentaListNew = new ArrayList<>();
            for (Venta ventaListNewVentaToAttach : ventaListNew) {
                ventaListNewVentaToAttach = em.getReference(ventaListNewVentaToAttach.getClass(), ventaListNewVentaToAttach.getVentaID());
                attachedVentaListNew.add(ventaListNewVentaToAttach);
            }
            ventaListNew = attachedVentaListNew;
            usuario.setVentaList(ventaListNew);
            List<Abono> attachedAbonoListNew = new ArrayList<>();
            for (Abono abonoListNewAbonoToAttach : abonoListNew) {
                abonoListNewAbonoToAttach = em.getReference(abonoListNewAbonoToAttach.getClass(), abonoListNewAbonoToAttach.getAbonoID());
                attachedAbonoListNew.add(abonoListNewAbonoToAttach);
            }
            abonoListNew = attachedAbonoListNew;
            usuario.setAbonoList(abonoListNew);
            List<Gasto> attachedGastoListNew = new ArrayList<>();
            for (Gasto gastoListNewGastoToAttach : gastoListNew) {
                gastoListNewGastoToAttach = em.getReference(gastoListNewGastoToAttach.getClass(), gastoListNewGastoToAttach.getGastoID());
                attachedGastoListNew.add(gastoListNewGastoToAttach);
            }
            gastoListNew = attachedGastoListNew;
            usuario.setGastoList(gastoListNew);
            List<Cotizacion> attachedCotizacionListNew = new ArrayList<>();
            for (Cotizacion cotizacionListNewCotizacionToAttach : cotizacionListNew) {
                cotizacionListNewCotizacionToAttach = em.getReference(cotizacionListNewCotizacionToAttach.getClass(), cotizacionListNewCotizacionToAttach.getCotizacionID());
                attachedCotizacionListNew.add(cotizacionListNewCotizacionToAttach);
            }
            cotizacionListNew = attachedCotizacionListNew;
            usuario.setCotizacionList(cotizacionListNew);
            List<Solicitud> attachedSolicitudListNew = new ArrayList<>();
            for (Solicitud solicitudListNewSolicitudToAttach : solicitudListNew) {
                solicitudListNewSolicitudToAttach = em.getReference(solicitudListNewSolicitudToAttach.getClass(), solicitudListNewSolicitudToAttach.getSolicitudID());
                attachedSolicitudListNew.add(solicitudListNewSolicitudToAttach);
            }
            solicitudListNew = attachedSolicitudListNew;
            usuario.setSolicitudList(solicitudListNew);
            List<Arqueo> attachedArqueoListNew = new ArrayList<>();
            for (Arqueo arqueoListNewArqueoToAttach : arqueoListNew) {
                arqueoListNewArqueoToAttach = em.getReference(arqueoListNewArqueoToAttach.getClass(), arqueoListNewArqueoToAttach.getArqueoID());
                attachedArqueoListNew.add(arqueoListNewArqueoToAttach);
            }
            arqueoListNew = attachedArqueoListNew;
            usuario.setArqueoList(arqueoListNew);
            usuario = em.merge(usuario);
            if (empleadoIDOld != null && !empleadoIDOld.equals(empleadoIDNew)) {
                empleadoIDOld.getUsuarioList().remove(usuario);
                empleadoIDOld = em.merge(empleadoIDOld);
            }
            if (empleadoIDNew != null && !empleadoIDNew.equals(empleadoIDOld)) {
                empleadoIDNew.getUsuarioList().add(usuario);
                empleadoIDNew = em.merge(empleadoIDNew);
            }
            for (Inventariodetalleacciones inventariodetalleaccionesListNewInventariodetalleacciones : inventariodetalleaccionesListNew) {
                if (!inventariodetalleaccionesListOld.contains(inventariodetalleaccionesListNewInventariodetalleacciones)) {
                    Usuario oldUsuarioIDOfInventariodetalleaccionesListNewInventariodetalleacciones = inventariodetalleaccionesListNewInventariodetalleacciones.getUsuarioID();
                    inventariodetalleaccionesListNewInventariodetalleacciones.setUsuarioID(usuario);
                    inventariodetalleaccionesListNewInventariodetalleacciones = em.merge(inventariodetalleaccionesListNewInventariodetalleacciones);
                    if (oldUsuarioIDOfInventariodetalleaccionesListNewInventariodetalleacciones != null && !oldUsuarioIDOfInventariodetalleaccionesListNewInventariodetalleacciones.equals(usuario)) {
                        oldUsuarioIDOfInventariodetalleaccionesListNewInventariodetalleacciones.getInventariodetalleaccionesList().remove(inventariodetalleaccionesListNewInventariodetalleacciones);
                        oldUsuarioIDOfInventariodetalleaccionesListNewInventariodetalleacciones = em.merge(oldUsuarioIDOfInventariodetalleaccionesListNewInventariodetalleacciones);
                    }
                }
            }
            for (Compra compraListNewCompra : compraListNew) {
                if (!compraListOld.contains(compraListNewCompra)) {
                    Usuario oldUsuarioIDOfCompraListNewCompra = compraListNewCompra.getUsuarioID();
                    compraListNewCompra.setUsuarioID(usuario);
                    compraListNewCompra = em.merge(compraListNewCompra);
                    if (oldUsuarioIDOfCompraListNewCompra != null && !oldUsuarioIDOfCompraListNewCompra.equals(usuario)) {
                        oldUsuarioIDOfCompraListNewCompra.getCompraList().remove(compraListNewCompra);
                        oldUsuarioIDOfCompraListNewCompra = em.merge(oldUsuarioIDOfCompraListNewCompra);
                    }
                }
            }
            for (Venta ventaListNewVenta : ventaListNew) {
                if (!ventaListOld.contains(ventaListNewVenta)) {
                    Usuario oldUsuarioIDOfVentaListNewVenta = ventaListNewVenta.getUsuarioID();
                    ventaListNewVenta.setUsuarioID(usuario);
                    ventaListNewVenta = em.merge(ventaListNewVenta);
                    if (oldUsuarioIDOfVentaListNewVenta != null && !oldUsuarioIDOfVentaListNewVenta.equals(usuario)) {
                        oldUsuarioIDOfVentaListNewVenta.getVentaList().remove(ventaListNewVenta);
                        oldUsuarioIDOfVentaListNewVenta = em.merge(oldUsuarioIDOfVentaListNewVenta);
                    }
                }
            }
            for (Abono abonoListNewAbono : abonoListNew) {
                if (!abonoListOld.contains(abonoListNewAbono)) {
                    Usuario oldUsuarioIDOfAbonoListNewAbono = abonoListNewAbono.getUsuarioID();
                    abonoListNewAbono.setUsuarioID(usuario);
                    abonoListNewAbono = em.merge(abonoListNewAbono);
                    if (oldUsuarioIDOfAbonoListNewAbono != null && !oldUsuarioIDOfAbonoListNewAbono.equals(usuario)) {
                        oldUsuarioIDOfAbonoListNewAbono.getAbonoList().remove(abonoListNewAbono);
                        oldUsuarioIDOfAbonoListNewAbono = em.merge(oldUsuarioIDOfAbonoListNewAbono);
                    }
                }
            }
            for (Gasto gastoListNewGasto : gastoListNew) {
                if (!gastoListOld.contains(gastoListNewGasto)) {
                    Usuario oldUsuarioIDOfGastoListNewGasto = gastoListNewGasto.getUsuarioID();
                    gastoListNewGasto.setUsuarioID(usuario);
                    gastoListNewGasto = em.merge(gastoListNewGasto);
                    if (oldUsuarioIDOfGastoListNewGasto != null && !oldUsuarioIDOfGastoListNewGasto.equals(usuario)) {
                        oldUsuarioIDOfGastoListNewGasto.getGastoList().remove(gastoListNewGasto);
                        oldUsuarioIDOfGastoListNewGasto = em.merge(oldUsuarioIDOfGastoListNewGasto);
                    }
                }
            }
            for (Cotizacion cotizacionListNewCotizacion : cotizacionListNew) {
                if (!cotizacionListOld.contains(cotizacionListNewCotizacion)) {
                    Usuario oldUsuarioIDOfCotizacionListNewCotizacion = cotizacionListNewCotizacion.getUsuarioID();
                    cotizacionListNewCotizacion.setUsuarioID(usuario);
                    cotizacionListNewCotizacion = em.merge(cotizacionListNewCotizacion);
                    if (oldUsuarioIDOfCotizacionListNewCotizacion != null && !oldUsuarioIDOfCotizacionListNewCotizacion.equals(usuario)) {
                        oldUsuarioIDOfCotizacionListNewCotizacion.getCotizacionList().remove(cotizacionListNewCotizacion);
                        oldUsuarioIDOfCotizacionListNewCotizacion = em.merge(oldUsuarioIDOfCotizacionListNewCotizacion);
                    }
                }
            }
            for (Solicitud solicitudListNewSolicitud : solicitudListNew) {
                if (!solicitudListOld.contains(solicitudListNewSolicitud)) {
                    Usuario oldUsuarioIDOfSolicitudListNewSolicitud = solicitudListNewSolicitud.getUsuarioID();
                    solicitudListNewSolicitud.setUsuarioID(usuario);
                    solicitudListNewSolicitud = em.merge(solicitudListNewSolicitud);
                    if (oldUsuarioIDOfSolicitudListNewSolicitud != null && !oldUsuarioIDOfSolicitudListNewSolicitud.equals(usuario)) {
                        oldUsuarioIDOfSolicitudListNewSolicitud.getSolicitudList().remove(solicitudListNewSolicitud);
                        oldUsuarioIDOfSolicitudListNewSolicitud = em.merge(oldUsuarioIDOfSolicitudListNewSolicitud);
                    }
                }
            }
            for (Arqueo arqueoListNewArqueo : arqueoListNew) {
                if (!arqueoListOld.contains(arqueoListNewArqueo)) {
                    Usuario oldUsuarioIDOfArqueoListNewArqueo = arqueoListNewArqueo.getUsuarioID();
                    arqueoListNewArqueo.setUsuarioID(usuario);
                    arqueoListNewArqueo = em.merge(arqueoListNewArqueo);
                    if (oldUsuarioIDOfArqueoListNewArqueo != null && !oldUsuarioIDOfArqueoListNewArqueo.equals(usuario)) {
                        oldUsuarioIDOfArqueoListNewArqueo.getArqueoList().remove(arqueoListNewArqueo);
                        oldUsuarioIDOfArqueoListNewArqueo = em.merge(oldUsuarioIDOfArqueoListNewArqueo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (IllegalOrphanException ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getUsuarioID();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getUsuarioID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Inventariodetalleacciones> inventariodetalleaccionesListOrphanCheck = usuario.getInventariodetalleaccionesList();
            for (Inventariodetalleacciones inventariodetalleaccionesListOrphanCheckInventariodetalleacciones : inventariodetalleaccionesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Inventariodetalleacciones " + inventariodetalleaccionesListOrphanCheckInventariodetalleacciones + " in its inventariodetalleaccionesList field has a non-nullable usuarioID field.");
            }
            List<Compra> compraListOrphanCheck = usuario.getCompraList();
            for (Compra compraListOrphanCheckCompra : compraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Compra " + compraListOrphanCheckCompra + " in its compraList field has a non-nullable usuarioID field.");
            }
            List<Venta> ventaListOrphanCheck = usuario.getVentaList();
            for (Venta ventaListOrphanCheckVenta : ventaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Venta " + ventaListOrphanCheckVenta + " in its ventaList field has a non-nullable usuarioID field.");
            }
            List<Abono> abonoListOrphanCheck = usuario.getAbonoList();
            for (Abono abonoListOrphanCheckAbono : abonoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Abono " + abonoListOrphanCheckAbono + " in its abonoList field has a non-nullable usuarioID field.");
            }
            List<Gasto> gastoListOrphanCheck = usuario.getGastoList();
            for (Gasto gastoListOrphanCheckGasto : gastoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Gasto " + gastoListOrphanCheckGasto + " in its gastoList field has a non-nullable usuarioID field.");
            }
            List<Cotizacion> cotizacionListOrphanCheck = usuario.getCotizacionList();
            for (Cotizacion cotizacionListOrphanCheckCotizacion : cotizacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Cotizacion " + cotizacionListOrphanCheckCotizacion + " in its cotizacionList field has a non-nullable usuarioID field.");
            }
            List<Solicitud> solicitudListOrphanCheck = usuario.getSolicitudList();
            for (Solicitud solicitudListOrphanCheckSolicitud : solicitudListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Solicitud " + solicitudListOrphanCheckSolicitud + " in its solicitudList field has a non-nullable usuarioID field.");
            }
            List<Arqueo> arqueoListOrphanCheck = usuario.getArqueoList();
            for (Arqueo arqueoListOrphanCheckArqueo : arqueoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Arqueo " + arqueoListOrphanCheckArqueo + " in its arqueoList field has a non-nullable usuarioID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empleado empleadoID = usuario.getEmpleadoID();
            if (empleadoID != null) {
                empleadoID.getUsuarioList().remove(usuario);
                empleadoID = em.merge(empleadoID);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
