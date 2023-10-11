def change(i,rnk):
    s=str(i)

    if len(s)>rnk+1:
        if int(s[-rnk-1])>=5:
            return str(int(s[:-rnk-1])+1)+"0"*(rnk+1)
        else:
            return s[:-rnk-1]+"0"*(rnk+1)


    elif len(s)==rnk+1:
        if int(s[0])>=5:
            return "1"+"0"*len(s)
        else:
            return "0"*len(s)
    else:
        return "0"

x,k=map(int,input().split())

for i in range(k):
    x=change(x,i)
print(x)