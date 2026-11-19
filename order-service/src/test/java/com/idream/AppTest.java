package com.idream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class AppTest { @Test void pageContainsTitle(){assertTrue(App.html("sample").contains("Order Service"));} }
