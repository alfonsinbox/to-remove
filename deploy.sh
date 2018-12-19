env=$1
case $env in
    "development") 
        echo "dev environment" 
        docker build -t bare-alpine:alpinebash seagul/build
    ;;
    "production") 
        echo "prod" 
    ;;
    *) 
        echo "bad envo" 
    ;;
esac

echo "done"