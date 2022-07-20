printf "## Backend 패키지를 준비합니다. ##\n"
GradleW="gradlew"
JarFile="./build/libs/web-0.0.1-SNAPSHOT.jar"

if [ -e $GradleW ] ; then
    echo "gradlew이 존재합니다."
    echo "어사 프로젝트 빌드를 시작합니다."
    # echo -e "`./gradlew build`\n" 

    if [ -e ${JarFile} ] ; then
        echo "패키지 파일을 찾았습니다."
        printf "## Backend를 시작할 준비가 완료되었습니다. ##"
    else
        echo "패키지 파일을 찾지 못했습니다."
        printf "## Backend를 시작할 수 없습니다. ##"
    fi

else
    echo "gradlew이 존재하지 않습니다."
fi

print "## FrontEnd 패키지를 준비합니다. ##\n"
# if [ -e ${JarFile} ] ; then
#     echo "패키지 파일을 찾았습니다."
# else
#     echo "패키지 파일을 찾지 못했습니다."
# fi




