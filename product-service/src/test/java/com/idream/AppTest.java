package com.idream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class AppTest { @Test void productContainsName(){assertTrue(App.productJson().contains("DevOps Course"));} }
