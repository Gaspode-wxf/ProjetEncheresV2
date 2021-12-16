package fr.eni.projet.encheres.bll;

import java.util.List;

/**
 * @author William "Gaspode" Freyer
 *
 */

public interface AbstractManager<T> {

	public abstract List<T> getCatalogue() throws BLLException;

	public abstract void addItem(T t) throws BLLException;

	public abstract void updateItem(T t) throws BLLException;

	public abstract void removeItem(T t) throws BLLException;

	public abstract void validerItem(T t) throws BLLException;

	public abstract T getItem(int index) throws BLLException;

	public abstract void nettoyerBDD() throws BLLException;

}
