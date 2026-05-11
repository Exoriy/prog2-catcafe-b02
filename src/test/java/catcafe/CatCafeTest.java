package catcafe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CatCafeTest {

  @Test
  void getCatCountReturnsZeroForEmptyCafe() {
    // given
    CatCafe cafe = new CatCafe();

    // when
    long catCount = cafe.getCatCount();

    // then
    assertEquals(0, catCount);
  }

  @Test
  void getCatCountReturnsOneAfterAddingOneCat() {
    // given
    CatCafe cafe = new CatCafe();
    FelineOverLord cat = new FelineOverLord("Morticia", 3);

    // when
    cafe.addCat(cat);

    // then
    assertEquals(1, cafe.getCatCount());
  }

  @Test
  void getCatCountReturnsNumberOfAddedCats() {
    // given
    CatCafe cafe = new CatCafe();

    // when
    cafe.addCat(new FelineOverLord("Morticia", 3));
    cafe.addCat(new FelineOverLord("Gwenapurr Esmeralda", 4));
    cafe.addCat(new FelineOverLord("Fitzby Darnsworth", 5));

    // then
    assertEquals(3, cafe.getCatCount());
  }

  @Test
  void addCatThrowsNullPointerExceptionForNullCat() {
    // given
    CatCafe cafe = new CatCafe();

    // when / then
    assertThrows(NullPointerException.class, () -> cafe.addCat(null));
  }

  @Test
  void getCatByNameReturnsCatWithMatchingName() {
    // given
    CatCafe cafe = new CatCafe();
    FelineOverLord morticia = new FelineOverLord("Morticia", 3);
    cafe.addCat(morticia);

    // when
    FelineOverLord result = cafe.getCatByName("Morticia");

    // then
    assertSame(morticia, result);
  }

  @Test
  void getCatByNameReturnsNullForUnknownName() {
    // given
    CatCafe cafe = new CatCafe();
    cafe.addCat(new FelineOverLord("Morticia", 3));

    // when
    FelineOverLord result = cafe.getCatByName("Unknown Cat");

    // then
    assertNull(result);
  }

  @Test
  void getCatByNameReturnsNullForNullName() {
    // given
    CatCafe cafe = new CatCafe();
    cafe.addCat(new FelineOverLord("Morticia", 3));

    // when
    FelineOverLord result = cafe.getCatByName(null);

    // then
    assertNull(result);
  }

  @Test
  void getCatByWeightReturnsCatWhenWeightIsInsideRange() {
    // given
    CatCafe cafe = new CatCafe();
    FelineOverLord cat = new FelineOverLord("Morticia", 3);
    cafe.addCat(cat);

    // when
    FelineOverLord result = cafe.getCatByWeight(3, 4);

    // then
    assertSame(cat, result);
  }

  @Test
  void getCatByWeightIncludesLowerLimit() {
    // given
    CatCafe cafe = new CatCafe();
    FelineOverLord cat = new FelineOverLord("Boundary Cat", 3);
    cafe.addCat(cat);

    // when
    FelineOverLord result = cafe.getCatByWeight(3, 5);

    // then
    assertSame(cat, result);
  }

  @Test
  void getCatByWeightExcludesUpperLimit() {
    // given
    CatCafe cafe = new CatCafe();
    cafe.addCat(new FelineOverLord("Too Heavy Cat", 5));

    // when
    FelineOverLord result = cafe.getCatByWeight(3, 5);

    // then
    assertNull(result);
  }
}
