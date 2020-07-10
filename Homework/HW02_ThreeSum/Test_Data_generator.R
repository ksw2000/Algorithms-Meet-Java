list = sample(x=-100000:100000,size=10000,replace=F)

sink("test_data.txt")
for (i in list){
    cat(i)
    cat('\n')
}
sink()
