<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}! </td>
	    		</tr>
	    		<tr>
	    			<td>Your payment confirmed!</td>
	    		</tr>
	    		<tr> 
	    			<td>
		    			Project details <br />
		    			The project #${Project.id} for your purchase on (${Transaction.createdAt?date}).
		    		</td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					Project description:  ${Project.summary} <br />
	    					${Project.title} work on the project  - ${Pilot.firstName} ${Pilot.lastName} <br />
	    					Project Total: ${Transaction.amount}${Transaction.currency}
	    				</p>
	    			</td>
	    		</tr>
	    		<tr> 
	    			<td>
	    				View Project Status(<a style="color:blue;font-weight:normal" href="${dashboardUrl}" target="_blank">${dashboardUrl}</a>)
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl"> 