#include<bits/stdc++.h>
using namespace std;

class segment_tree
{
private:
	vector<int>st,A;
	int n;

	void build(int p,int L,int R)
	{
		if(L==R){
			st[p]=R;
			return ;
		}
		
		build((p<<1), L, (L+R)/2);
		build( ( (p<<1) +1), ((L+R)/2)+1,R);
		
		int p1=st[(p<<1)],p2=st[(p<<1)+1];
		st[p]= (A[p1]<=A[p2])?p1 : p2;
	}

	int rmq(int p ,int L,int R ,int i ,int j)
	{
		if(j<L || R<i)  // i--j L--R || L--R i--j
			return -1;
		if(i<=L && R<=j) //  i--L--R--j
			return st[p];

		int p1=rmq(p<<1, L, ((L+R)>>1),i,j);
		int p2=rmq((p<<1)+1, ((L+R)>>1)+1, R,i,j);
		
		if(p1==-1)
			return p2;
		if(p2==-1)
			return p1;

		return (A[p1]<=A[p2]) ? p1:p2 ;

	}

	int upd(int p,int L,int R,int i,int v)
	{
		if(i<L || i>R)
			return st[p];

		if(L==R &&R ==i)
		{
			A[i]=v;
			return st[p]=i;
		}
		int p1=upd(p<<1,L,(L+R)>>1,i,v);
		int p2=upd((p<<1)+1,((L+R)>>1)+1,R,i,v);

		return (A[p1]<=A[p2]) ? p1:p2 ; 
	}

public:
	segment_tree(vector<int>& _A)
	{
		
		A=_A;
		n=A.size();
		st.assign(4*n,0);
		build(1,0,n-1);

	}
	int rmq(int i,int j){return rmq(1,0,n-1,i,j);}
	int upd(int i,int v){return upd(1,0,n-1,i,v);}
};

int main(){
	vector<int>B={1,2,3,4,5};
	segment_tree bt(B);
	cout<<B[bt.rmq(2,4)];
	bt.upd(2,10);
	cout<<B[bt.rmq(2,4)];
	return 0;	
}