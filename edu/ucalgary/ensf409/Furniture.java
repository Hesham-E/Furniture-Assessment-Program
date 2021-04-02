public class Furniture {
    
    public String category; 
    public Chair chair = null; 
    public Desk desk = null;
    public Filing filing = null; 
    public Lamp lamp = null; 

    public Furniture(String type, String category, String ID, boolean b1, boolean b2, boolean b3, boolean b4, int price, String manuID)
    { 
        this.category = category; 
        if (this.category == "chair")
        {
            this.chair = new Chair(ID, type, b1, b2, b3, b4, price, manuID);
        }
        if (this.category == "desk")
        {
            this.desk = new Desk(ID, type, b1, b2, b3, price, manuID);
        }
        if (this.category == "lamp")
        {
            this.lamp = new Lamp(ID, type, b1, b2, price, manuID);
        }
        if (this.category == "filing")
        {
            this.filing = new Filing(ID, type, b1, b2, b3, price, manuID);
        }
    }

    public Furniture(String type, String category)
    {
        this.category = category; 
        if (this.category == "chair")
        {
            Chair chair = new Chair("null", type, false, false, false, false, 0, "null");
        }
        if (this.category == "desk")
        {
            Desk desk = new Desk("null", type, false, false, false, 0, "null");
        }
        if (this.category == "lamp")
        {
            Lamp lamp = new Lamp("null", type, false, false, 0, "null");
        }
        if (this.category == "filing")
        {
            Filing filing = new Filing("null", type, false, false, false, 0, "null");
        }
    }

    public boolean isFilled ()
    {
        if (this.category == "chair")
        {
            return (this.chair.legs && this.chair.arms && this.chair.seat && this.chair.cushion)? true : false;    
        }
        if (this.category == "desk")
        {
            return (this.desk.legs && this.desk.drawer && this.desk.top)? true : false; 
        }
        if (this.category == "lamp")
        {
            return (this.lamp.base && this.lamp.bulb)? true : false; 
        }
        if (this.category == "filing")
        {
            return (this.filing.cabinet && this.filing.drawers && this.filing.rails)? true : false; 
        }
        return false;
    }

    public String getID()
    {
        if (this.category == "chair") 
            return this.chair.ID; 
        if (this.category == "desk")
            return this.desk.ID;
        if (this.category == "lamp")
            return this.lamp.ID; 
        if (this.category == "filing")
            return this.filing.ID;
        return "not found";
    }

    public int getPrice()
    {
        if (this.category == "chair") 
            return this.chair.price; 
        if (this.category == "desk")
            return this.desk.price;
        if (this.category == "lamp")
            return this.lamp.price; 
        if (this.category == "filing")
            return this.filing.price;
        return 0; 
    }

    private class Desk{
        public String ID; 
        public String type; 
        public boolean legs; 
        public boolean top; 
        public boolean drawer;  
        public int price; 
        public String manuID;

        public Desk(String ID, String type, boolean legs, boolean top, boolean drawer, int price, String manuID)
        {
            this.ID = ID; 
            this.type = type; 
            this.price = price; 
            this.manuID = manuID; 
            this.legs = legs; 
            this.top = top; 
            this.drawer = drawer; 
        }
    }

    private class Lamp{
        public String ID; 
        public String type; 
        public boolean base; 
        public boolean bulb;  
        public int price; 
        public String manuID;

        public Lamp(String ID, String type, boolean base, boolean bulb, int price, String manuID) 
        {
            this.ID = ID; 
            this.type = type; 
            this.price = price; 
            this.manuID = manuID; 
            this.base = base; 
            this.bulb = bulb; 
        }
        
    }

    private class Chair{
        public String ID; 
        public String type; 
        public boolean legs; 
        public boolean arms; 
        public boolean seat; 
        public boolean cushion; 
        public int price; 
        public String manuID;

        public Chair(String ID, String type, boolean legs, boolean arms, boolean seat, boolean cushion, int price, String manuID)
        {
            this.ID = ID; 
            this.type = type; 
            this.price = price; 
            this.manuID = manuID; 
            this.legs = legs; 
            this.arms = arms; 
            this.seat = seat; 
            this.cushion = cushion;  
        }
    }

    private class Filing{
        public String ID; 
        public String type; 
        public boolean rails; 
        public boolean drawers; 
        public boolean cabinet;  
        public int price; 
        public String manuID;

        public Filing(String ID, String type, boolean rails, boolean drawers, boolean cabinet, int price, String manuID) 
        {
            this.ID = ID; 
            this.type = type; 
            this.price = price; 
            this.manuID = manuID; 
            this.rails = rails; 
            this.drawers = drawers; 
            this.cabinet = cabinet; 
        }

    }
}
