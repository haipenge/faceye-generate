#自动构建脚本:

ROOT=$(dirname $(cd "$(dirname "$0")";pwd))
if [[ $1 == 'all' ]];then
  cd $ROOT/faceye-generate-entity
  mvn clean install -D maven.test.skip=true 
fi
cd $ROOT/faceye-generate-manager
mvn clean jetty:run -D maven.test.skip=true
