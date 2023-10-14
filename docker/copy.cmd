@echo off

echo "begin copy html "
del /Q /S ".\nginx\html\dist\"
copy "..\ruoyi-ui\dist" ".\nginx\html\dist\"

echo "begin copy ruoyi-gateway"
del /Q /S ".\ruoyi\gateway\jar\"
copy "..\ruoyi-gateway\target\ruoyi-gateway.jar" ".\ruoyi\gateway\jar\"


echo "begin copy ruoyi-auth "
del /Q /S ".\ruoyi\auth\jar\"
copy "..\ruoyi-auth\target\ruoyi-auth.jar" ".\ruoyi\auth\jar\"

echo "begin copy ruoyi-modules-system "
del /Q /S ".\ruoyi\modules\system\jar\"
copy "..\ruoyi-modules\ruoyi-system\target\ruoyi-modules-system.jar" ".\ruoyi\modules\system\jar\"

echo "begin copy ruoyi-modules-file "
del /Q /S ".\ruoyi\modules\file\jar\"
copy "..\ruoyi-modules\ruoyi-file\target\ruoyi-modules-file.jar" ".\ruoyi\modules\file\jar\"

echo "begin copy ruoyi-modules-gen "
del /Q /S ".\ruoyi\modules\gen\jar\"
copy "..\ruoyi-modules\ruoyi-gen\target\ruoyi-modules-gen.jar" ".\ruoyi\modules\gen\jar\"

echo "begin copy ruoyi-modules-client "
del /Q /S ".\ruoyi\modules\client\jar\"
copy "..\ruoyi-modules\ruoyi-client\target\ruoyi-modules-client.jar" ".\ruoyi\modules\client\jar\"


