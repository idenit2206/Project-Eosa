printf "## Backend 패키지를 준비합니다. ##\n"
GradleW="gradlew"
JarFile="./build/libs/web-0.0.1-SNAPSHOT.jar"
JarFileName="web-0.0.1-SNAPSHOT.jar"
CURRENT_DIR=`pwd`


JarPort = ps | jar

kill -9 $JarPort

if [ -e $GradleW ] ; then
    echo "gradlew이 존재합니다."
    echo "어사 프로젝트 빌드를 시작합니다."
    # echo -e "`./gradlew build`\n" 

    if [ -e ${JarFile} ] ; then
        echo "패키지 파일을 찾았습니다."
        printf "## Backend를 시작할 준비가 완료되었습니다. ##\n"
        printf "## Backend서비스를 시작합니다.\n"
        # jar를 background에서 실행
        nohup java -jar ./build/libs/web-0.0.1-SNAPSHOT.jar &

    else
        echo "패키지 파일을 찾지 못했습니다."
        printf "## Backend를 시작할 수 없습니다. ##"
        break
    fi

else
    echo "gradlew이 존재하지 않습니다."
fi

printf "## FrontEnd 패키지를 준비합니다. ##\n"
echo "FrontEnd 패키지를 빌드합니다."

FRONT_DIR="./src/main/resources/web-ui"
cd $FRONT_DIR && npm install && npm run build
echo "FrontEnd 패키지를 시작합니다."
npx serve -s build
