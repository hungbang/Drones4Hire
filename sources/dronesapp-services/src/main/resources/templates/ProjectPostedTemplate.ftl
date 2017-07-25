<#include "HeaderBlackList.ftl">
<tr>
    <td>
        <table width="500" border="0" cellspacing="0" cellpadding="0" height="40">
            <tr>
                <th scope="col" width="340" bgcolor="#1f2532" height="40">
                    <table width="340" border="0" cellspacing="0" cellpadding="0" height="40">
                        <tr>
                            <th scope="col" width="20"></th>
                            <th scope="col" align="left"><font color="#3ebcc7" size="2"><b>JOB POSTING</b></font></th>
                            <th scope="col" ></th>
                        </tr>
                    </table>
                </th>
                <th scope="col" width="160" bgcolor="#181d27">
                    <table width="160" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <th scope="col" width="20"></th>
                            <th scope="col" align="left"><font color="#3ebcc7" size="2"><b>BUDGET</b></font></th>
                            <th scope="col">&nbsp;</th>
                        </tr>
                    </table>
                </th>
            </tr>
        </table>
    </td>
</tr>
<tr>
    <td height="10"></td>
</tr>
<#list Projects as project>
<tr>
    <td>
        <table width="500" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
            <tr>
                <th scope="col" width="340" valign="top">
                    <table width="340" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <th rowspan="7" scope="col" width="20"></th>
                            <th scope="col" height="15"></th>
                        </tr>
                        <tr>
                            <td align="left"><font color="#3ebcc7" size="3"><b>${project.title}</b></font></td>
                        </tr>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                            <td align="left" style="line-height:16px; font-weight:normal;"><font color="#666666" size="2">
                                ${project.summary}<br>
                                <a href="${baseUrl}/${project.id}${descriptionSuffix}" target="_blank" style="color: #3ebcc7" >See more</a>
                            </font>
                            </td>
                        </tr>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                            <td align="left" style="font-weight:normal; font-size:10px;"><font color="#666666" >
                                <b>Location:</b> (<#if project.location.state??>${project.location.state.name}, </#if>${project.location.country.name})</font>
                            </td>
                        </tr>
                        <tr>
                            <td height="15"></td>
                        </tr>
                    </table>
                </th>
                <th scope="col" width="160" valign="top">
                    <table width="160" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <th rowspan="6" scope="col" width="20"></th>
                            <th scope="col" height="15"></th>
                        </tr>
                        <tr>
                            <td align="left" style="font-weight:bold;"><font color="#666666" size="3"><b>${project.budget.currency}</b></font></td>
                        </tr>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                            <td align="left"><font color="#666666" size="3"><b>${project.budget.title}</b></font></td>
                        </tr>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                            <td>
                                <table width="120" border="0" cellspacing="0" cellpadding="0">
                                    <tr>

                                        <th scope="col" align="center" width="160 px" style="padding:5px 8px 5px 10px;background-color:#3ebcc7;border-radius:5px;">
                                            <a href="${baseUrl}/${project.id}${descriptionSuffix}" style=" font-size:12px; font-family:helvetica neue,helvetica,arial,sans-serif;font-weight:bold;line-height:18px;color:#ffffff;text-decoration:none;display:block;text-align:center;max-width:200px;overflow:hidden;text-overflow:ellipsis;" target="_blank" data-saferedirecturl="#">
                                                VIEW JOB</a>
                                        </th>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </th>
            </tr>
        </table>
    </td>
</tr>
<tr>
    <td height="10"></td>
</tr>
</#list>
<#include "FooterBlackList.ftl">