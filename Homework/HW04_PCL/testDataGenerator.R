s=30000
list = sample(x=-900000:900000,size=s,replace=T)
list2 = sample(x=-900000:900000,size=s,replace=T)

sink("./test_data.txt")

i=1
while(i<=s){
    cat(list[i])
    cat(" ")
    cat(list2[i])
    cat("\n")
    i=i+1
}

sink()
