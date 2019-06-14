<%-- 
    Document   : builderPresentacion
    Created on : Jun 14, 2019, 3:14:58 AM
    Author     : Christian Carrillo Zúñiga
--%>
<%-- 
    Document   : index
    Created on : Jun 13, 2019, 11:31:22 PM
    Author     : Christian Carrillo Zúñiga
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<t:baseTemplate
    pageTitle="Builder"
    navbarBgColor="bg-danger"
    >
    <t:mainContentTemplate>

        <main id="main">

            <!--==========================
              About Us Section
            ============================-->
            <section id="about">
                <div class="container">

                    <header class="section-header">
                        <h3>Builder</h3>
                    </header>

                    <div class="row about-cols mb-5">                        

                        <div class="col-md-6 wow fadeInUp" data-wow-delay="1s">
                            <div class="about-col shadow">
                                <div class="img d-flex justify-content-center">
                                    <img src="<c:url value="/resources/img/lightbulb.png"/>" alt="" class="img-fluid">                                    
                                </div>
                                <h2 class="title"><a href="#">CONCEPTO</a></h2>
                                <h5 class="text-justify p-4">
                                    Permite la creación de una variedad de objetos complejos desde un objeto fuente, el cual se compone de una variedad de partes que contribuyen individualmente a la creación de cada objeto complejo.
                                </h5>
                            </div>
                        </div>

                        <div class="col-md-6 wow fadeInUp" data-wow-delay="1.25s">
                            <div class="about-col shadow">
                                <div class="img d-flex justify-content-center">
                                    <img src="<c:url value="/resources/img/vision.png"/>" alt="" class="img-fluid">
                                </div>
                                <h2 class="title"><a href="#">INTENCIÓN</a></h2>
                                <h5 class="text-justify p-4">
                                    Abstrae el proceso de creación de un objeto complejo, centralizando dicho proceso en un único punto, de tal forma que el mismo proceso de construcción pueda crear representaciones diferentes.
                                </h5>
                            </div>
                        </div>

                    </div>

                </div>
            </section><!-- #about -->


            <!--==========================
              Call To Action Section
            ============================-->
            <section id="call-to-action" class="wow fadeIn">

                <div class="container">

                    <header class="section-header wow fadeInUp">
                        <h3>¿CUANDO UTILIZARLO?</h3>
                    </header>

                    <div class="row">

                        <div class="col-md-6 box wow bounceInUp" data-wow-delay="0.5s" data-wow-duration="1.4s">
                            <div class="icon"><i class="ion-ios-barcode-outline"></i></div>
                            <h4 class="title"><a class="text-light text-justify" href="">Cuando se necesite Independizar el algoritmo de creación de un objeto complejo de las partes que constituyen el objeto y cómo se ensamblan entre ellas.</a></h4>
                        </div>

                        <div class="col-md-6 box wow bounceInUp" data-wow-delay="1s" data-wow-duration="1.9s">
                            <div class="icon"><i class="ion-ios-barcode-outline"></i></div>
                            <h4 class="title"><a class="text-light text-justify" href="">Cuando el proceso de construcción permita distintas representaciones para el objeto construido, de manera dinámica.</a></h4>
                        </div>

                    </div>
                </div>

                <div class="container">
                    <div class="row">

                        <div class="col-md-6 box wow bounceInUp" data-wow-delay="1.5s" data-wow-duration="2.4s">
                            <div class="icon"><i class="ion-ios-barcode-outline"></i></div>
                            <h4 class="title"><a class="text-light text-justify" href="">Se quieren llevar a cabo muchas operaciones dispares sobre objetos de una estructura de objetos sin tener que incluir dichas operaciones en las clases.</a></h4>
                        </div>

                        <div class="col-md-6 box wow bounceInUp" data-wow-delay="2s" data-wow-duration="2.9s">
                            <div class="icon"><i class="ion-ios-barcode-outline"></i></div>
                            <h4 class="title"><a class="text-light text-justify" href="">Se quieren llevar a cabo muchas operaciones dispares sobre objetos de una estructura de objetos sin tener que incluir dichas operaciones en las clases.</a></h4>
                        </div>

                    </div>
                </div>

                <div class="container text-center wow bounceInUp my-4" data-wow-delay="3s" data-wow-duration="3.9s">
                    <h3>Además</h3>
                    <h5 class="text-light">Dado que este patrón separa un algoritmo de la estructura de un objeto, es ampliamente utilizado en intérpretes, compiladores y procesadores de lenguajes, en general.
                        Se debe utilizar este patrón si se quiere realizar un cierto número de operaciones, que no están relacionadas entre sí, sobre instancias de un conjunto de clases, y no se quiere "contaminar" a dichas clases.</h5>

                </div>                

            </section><!-- #call-to-action -->           

            <!--==========================
              Facts Section
            ============================-->
            <section id="facts"  class="wow fadeIn" data-wow-delay="0.5s" data-wow-duration="1.4s">
                <div class="container">

                    <header class="section-header wow fadeInUpBig">
                        <h3>Diagrama de Clases</h3>
                    </header>

                    <div class="row counters">                        

                        <div class="col-lg-3 col-6 wow fadeInDown">
                            <h6 class="text-success text-center"><strong>Producto</strong></h6>
                            <p>Representa el objeto complejo a construir.</p>
                        </div> 

                        <div class="col-lg-3 col-6  wow fadeInDown">
                            <h6 class="text-success text-center"><strong>Builder</strong></h6>
                            <p>Especifica una interface abstracta para la creación de las partes del producto. Declara las operaciones necesarias para crear las partes de un objeto concreto.</p>
                        </div>

                        <div class="col-lg-3 col-6  wow fadeInDown">
                            <h6 class="text-success text-center"><strong>ConcreteBuilder</strong></h6>
                            <p>Implementa builder y ensambla las partes que constituyen el objeto complejo.</p>
                        </div>

                        <div class="col-lg-3 col-6  wow fadeInDown">
                            <h6 class="text-success text-center"><strong>Director</strong></h6>
                            <p>Construye un objeto usando la interfaz builder. Sólo debería ser necesario especificar su tipo y así reutilizar el mismo proceso para distintos tipos.</p>
                        </div>

                    </div>

                    <div class="facts-img">
                        <img src="<c:url value="/resources/img/builder-diagrama1.jpg"/>" alt="" class="img-diagrama">
                    </div>

                </div>
            </section><!-- #facts -->           

            <!--==========================
              Clients Section
            ============================-->
            <section id="testimonials" class="section-bg wow fadeInLeft">
                <div class="container">

                    <header class="section-header">
                        <a class="cta-btn" href="<c:url value="/builder/demo"/>"><h3>Demo</h3></a>
                    </header>

                </div>
            </section><!-- #testimonials -->   

        </main>

    </t:mainContentTemplate>

</t:baseTemplate>

<script>
    $("#main-content").removeClass("col-lg-8")
    $("#nav-builder").addClass("active")
</script>