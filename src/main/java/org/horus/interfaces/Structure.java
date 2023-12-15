package org.horus.interfaces;

import java.util.List;

public interface Structure {
  // zwraca dowolny element o podanym kolorze
  List<Block> findBlockByColor(String color);

  // zwraca wszystkie elementy z danego materiału
  List<Block> findBlocksByMaterial(String material);

  //zwraca liczbę wszystkich elementów tworzących strukturę
  int count();
}
