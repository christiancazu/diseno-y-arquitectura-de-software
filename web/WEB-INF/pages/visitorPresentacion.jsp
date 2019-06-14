<%-- 
    Document   : visitorPresentacion
    Created on : Jun 14, 2019, 5:33:49 AM
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
                        <h3>Visitor</h3>
                    </header>

                    <div class="row about-cols mb-5">                        

                        <div class="col-md-6 wow fadeInUp" data-wow-delay="1s">
                            <div class="about-col shadow">
                                <div class="img d-flex justify-content-center">
                                    <img src="<c:url value="/resources/img/lightbulb.png"/>" alt="" class="img-fluid">                                    
                                </div>
                                <h2 class="title"><a href="#">CONCEPTO</a></h2>
                                <h5 class="text-justify p-4">
                                    Busca separar un algoritmo de la estructura	de un objeto. La operación se implementa de forma que no se modifique el código de  las clases sobre las que opera.
Si un objeto es	el responsable de mantener un cierto tipo de información, entonces es lógico asignarle también la responsabilidad de realizar todas las operaciones necesarias sobre  esa información. La operación se define en cada una de las clases que representan los posibles tipos sobre los que se aplica dicha operación, y por medio	del polimorfismo y la vinculación dinámica se elige en tiempo de ejecución qué versión de la operación se debe ejecutar.
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
                                    Según el libro de GoF este patrón permite añadir funcionalidades a una clase sin tener que modificarla, siendo usado para manejar algoritmos, relaciones y responsabilidades entre objetos.
                                    <br><br>Así	pues,	nos		resultará		útil		cuando			necesitemos		realizar  operaciones		distintas	y	no	relacionadas	sobre	una		estructura	de  objetos,	aunque	si	lo		utilizamos	y	luego		tenemos	que	modificar  alguna de las clases implicadas, hemos de tener en cuenta que se  produce cierto nivel de acoplamiento entre ellas.
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
                            <h4 class="title"><a class="text-light text-justify" href="">Una estructura de objetos contiene muchas clases de objetos  con distintas interfaces y se desea llevar a cabo operaciones  sobre estos objetos que son distintas en cada clase concreta.</a></h4>
                        </div>

                        <div class="col-md-6 box wow bounceInUp" data-wow-delay="1s" data-wow-duration="1.9s">
                            <div class="icon"><i class="ion-ios-barcode-outline"></i></div>
                            <h4 class="title"><a class="text-light text-justify" href="">Se quieren llevar a cabo muchas operaciones dispares sobre  objetos	de	una	estructura	de	objetos	sin	tener	que	incluir  dichas operaciones en las clases.</a></h4>
                        </div>

                    </div>
                </div>

                <div class="container">
                    <div class="row">

                        <div class="col-md-6 box wow bounceInUp" data-wow-delay="1.5s" data-wow-duration="2.4s">
                            <div class="icon"><i class="ion-ios-barcode-outline"></i></div>
                            <h4 class="title"><a class="text-light text-justify" href="">Las clases que definen la estructura de objetos no cambian,  pero las operaciones que se llevan a cabo sobre ellas.</a></h4>
                        </div>

                        <div class="col-md-6 box wow bounceInUp" data-wow-delay="2s" data-wow-duration="2.9s">
                            <div class="icon"><i class="ion-ios-barcode-outline"></i></div>
                            <h4 class="title"><a class="text-light text-justify" href="">A medida que estas operaciones crecen, el número de operaciones que deberá tener la estructura también crecerá haciendo que administrar la estructura sea muy complejo.</a></h4>
                        </div>

                    </div>
                </div>

                <div class="container text-center wow bounceInUp my-4" data-wow-delay="3s" data-wow-duration="3.9s">
                    <h3>Además</h3>
                    <h5 class="text-light">El patrón Visitor propone una solución a este problema. Pretende independizar las clases de las operaciones que se ejecutan sobre ellas.</h5>

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

                    <div class="row counters justify-content-center">                        

                        <div class="col-lg-3 col-6 wow fadeInDown">
                            <h6 class="text-success text-center"><strong>Cliente</strong></h6>
                            <p>Componente que interactúa con la estructura (element) y con el Visitante, éste es responsable de crear los visitantes y enviarlos al elemento para su procesamiento.</p>
                        </div> 

                        <div class="col-lg-2 col-6 wow fadeInDown">
                            <h6 class="text-success text-center"><strong>Element</strong></h6>
                            <p>Representa la raíz de la estructura, en forma de árbol, sobre la que utilizaremos el Visitante. Este objeto por lo general es una interface que define el método accept y deberán implementar todos los objetos de la estructura.</p>
                        </div>

                        <div class="col-lg-2 col-6 wow fadeInDown">
                            <h6 class="text-success text-center"><strong>ConcreteElement</strong></h6>
                            <p>Representa un hijo de la estructura compuesta, la estructura completa puede estar compuesta de un gran número de estos objetos y cada uno deberá implementar el método accept.</p>
                        </div>

                        <div class="col-lg-2 col-6 wow fadeInDown">
                            <h6 class="text-success text-center"><strong>IVisitor</strong></h6>
                            <p>Interface que define la estructura del visitante, la interface deberá tener un método por cada objeto que se requiera analizar de la estructura (element).</p>
                        </div>
                        
                        <div class="col-lg-3 col-6 fadeInDown">
                            <h6 class="text-success text-center"><strong>ConcreteVisitor</strong></h6>
                            <p>Representa una implementación del visitante, esta implementación puede realizar una operación sobre el element. Es posible tener todos los ConcreteVisitor necesarios para realizar las operaciones que necesitemos.</p>
                        </div>

                    </div>

                    <div class="facts-img">
                        <img src="<c:url value="/resources/img/visitor-diagram.png"/>" alt="" class="img-diagrama">
                    </div>
                    
                    <div class="facts-img">
                        <img src="<c:url value="/resources/img/visitor-sequence.png"/>" alt="" class="img-diagrama">
                    </div>

                </div>
            </section><!-- #facts -->           

            <!--==========================
              Clients Section
            ============================-->
            <section id="testimonials" class="section-bg wow fadeInLeft">
                <div class="container">                            
                    <header class="section-header">
                        <a class="cta-btn" href="<c:url value="/visitor/demo"/>"><h3>Demo</h3></a>
                    </header>

                </div>
            </section><!-- #testimonials -->   

        </main>

    </t:mainContentTemplate>

</t:baseTemplate>

<script>
    $("#main-content").removeClass("col-lg-8")
    $("#nav-visitor").addClass("active")
</script>