T=int(input())



for i in range(T):
    N,X,Y=map(str,input().split())
    flag =  True
    for j in range(int(N)):
        if Y[j]=="C" and X[j]!="C":
            flag = False

    sx=""
    sy=""
    
    for j in range(int(N)):    
        if Y[j]!="C":
            sx+=X[j]
            sy+=Y[j]
        else:
            ya =0
            yb =0 
            xa =0
            xb =0
            for t in range(len(sx)):
                if sx[t] == "A":
                    xa+=1
                elif sx[t] =="B":
                    xb+=1
                if sy[t]=="A":
                    ya+=1
                elif sy[t]=="B":
                    yb+=1
            if xa > ya or xb > yb:
                flag =False
         
            nsx=""
            for t in range(len(sx)):
                if sx[t]=="C" and xa < ya:
                    xa+=1
                    nsx+="A"
                elif sx[t]=="C":
                    nsx+="B"
                else:
                    nsx+=sx[t]

            nsx_l=[]
            sy_l=[]
            for t in range(len(nsx)):
                if nsx[t]=="A":
                    nsx_l.append(t) 
                if sy[t]=="A":
                    sy_l.append(t)
            
            if len(nsx_l) == len(sy_l):
                for t in range(len(nsx_l)):
                    if nsx_l[t]>sy_l[t]:
                        
                        flag=False
            sx=""
            sy=""
    ya =0
    yb =0 
    xa =0
    xb =0
    for t in range(len(sx)):
        if sx[t] == "A":
            xa+=1
        elif sx[t] =="B":
            xb+=1
        if sy[t]=="A":
            ya+=1
        elif sy[t]=="B":
            yb+=1
    if xa > ya or xb > yb:
        flag =False
    nsx=""
    for t in range(len(sx)):
        if sx[t]=="C" and xa < ya:
            xa+=1
            nsx+="A"
        elif sx[t]=="C":
            nsx+="B"
        else:
            nsx+=sx[t]
    nsx_l=[]
    sy_l=[]
    
    for t in range(len(nsx)):
        if nsx[t]=="A":
            nsx_l.append(t) 
        if sy[t]=="A":
            sy_l.append(t)
                                
    if len(nsx_l) == len(sy_l):
        for t in range(len(nsx_l)):
            if nsx_l[t]>sy_l[t]:
                flag=False


    if flag == False:
        print("No")
    else:
        print("Yes")


    

        


        
