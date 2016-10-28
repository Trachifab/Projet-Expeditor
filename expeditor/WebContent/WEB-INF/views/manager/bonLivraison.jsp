<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="fr.eni.expeditor.entity.Commande"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bon de livraison</title>
</head>
<body>
	<%
		Commande col = (Commande) request.getAttribute("commande");
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		List<String> lstLigne = (List<String>) request.getAttribute("lstLignes");
	%>

	<div style="width: 15cm; border: 1px solid black; padding: 5px">

		<div style="margin: 5px">BON DE LIVRAISON</div>

		<div style="width: 100%; border: 1px solid black; margin-bottom: 5px">
			<div>Client: <%=col.getClient().getRaisonSociale() %></div>
			<div>Adresse:<%=col.getClient().getAdresse() %>, 35200 RENNES</div>
			<div>Date édition: <%=formater.format(new Date()) %></div>
		</div>
		 <div style="width: 100%; border: 1px solid black;">
			<%for(String ligne : lstLigne){ %>
				<div> - <%=ligne %></div>
			<%} %>
			<div
				style="width: 100%; border-top: 1px solid black; margin-top: 5px">Poids
				: <%=(String)request.getAttribute("poidsTotal") %>kg</div>
		</div> 
	</div>
</body>
</html>