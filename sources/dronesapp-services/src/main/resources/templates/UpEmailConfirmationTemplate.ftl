<#include "Header.ftl">
<div role="section">
    <div class="layout one-col fixed-width" style="Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;">
        <div class="layout__inner" style="border-collapse: collapse;display: table;width: 100%;background-color: #ffffff;">
            <!--[if (mso)|(IE)]><table align="center" cellpadding="0" cellspacing="0" role="presentation"><tr class="layout-fixed-width" style="background-color: #ffffff;"><td style="width: 600px" class="w560"><![endif]-->
            <div class="column" style="text-align: left;color: #8e959c;font-size: 14px;line-height: 21px;font-family: sans-serif;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);">

                <div style="Margin-left: 20px;Margin-right: 20px;">
                    <p class="size-16" style="Margin-top: 0;Margin-bottom: 0;font-family: arial,sans-serif;font-size: 16px;line-height: 24px;" lang="x-size-16"><span class="font-arial"><span style="color:rgb(5, 8, 10)">Hello ${User.username}! </span></span></p><p class="size-16" style="Margin-top: 20px;Margin-bottom: 20px;font-family: arial,sans-serif;font-size: 16px;line-height: 24px;" lang="x-size-16"><span class="font-arial"><span style="color:rgb(5, 8, 10)">Your account has not been verified. Please verify your account to gain access. </span></span></p>
                </div>

                <div style="Margin-left: 20px;Margin-right: 20px;">
                    <div class="btn btn--flat btn--large" style="text-align:center;">
                        <![if !mso]><a style="border-radius: 4px;display: inline-block;font-size: 14px;font-weight: bold;line-height: 24px;padding: 12px 24px;text-align: center;text-decoration: none !important;transition: opacity 0.1s ease-in;color: #ffffff !important;background-color: #13c1c4;font-family: Avenir, sans-serif;" href="${verifyUrl}">Verify Account</a><![endif]>
                        <!--[if mso]><p style="line-height:0;margin:0;">&nbsp;</p><v:roundrect xmlns:v="urn:schemas-microsoft-com:vml" href="${verifyUrl}" style="width:147px" arcsize="9%" fillcolor="#13C1C4" stroke="f"><v:textbox style="mso-fit-shape-to-text:t" inset="0px,11px,0px,11px"><center style="font-size:14px;line-height:24px;color:#FFFFFF;font-family:Avenir,sans-serif;font-weight:bold;mso-line-height-rule:exactly;mso-text-raise:4px">Verify<br>
						Account</center></v:textbox></v:roundrect><![endif]--></div>
                </div>

            </div>
            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->
        </div>
    </div>

    <div style="line-height:20px;font-size:20px;">&nbsp;</div>
<#include "Footer.ftl">