import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ModuleServiceTest {

  @Test
  public void shouldInstantiateModuleService(){
    assertNotNull(new ModuleService());
  }

  @Test
  public void shouldInstantiateModuleServiceOneMoreTime(){
    assertNotNull(new ModuleService());
  }
}
