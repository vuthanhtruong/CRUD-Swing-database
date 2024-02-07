package topica.edu.vn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;




public class CuaSoCuaToi<E> extends JFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel dtm;
	JTable tbJTable;
	JTextField txtID;
	JTextField txtTen;
	ButtonGroup btg;
	JRadioButton radNam;
	JRadioButton radNu;
	JTextField txtDiemToan;
	JTextField txtDiemVan;
	JTextField txtDiemAnh;
	JButton btnThem;
	JButton btnSua;
	JButton btnXoa;
	JButton btnXoaAll;
	JButton btnSapXep;
	JButton btnFile;
	JTextField txtSearch;
	JButton btnSearch;
	JScrollPane sc;
	Statement statement=null;
	Connection conn=null;
	File outFile=new File("data.txt");
	FileOutputStream fouts=null;
	DataOutputStream douts=null;
	FileInputStream fins=null;
	DataInputStream dins=null;
	JComboBox<String> cb;
	JTextField txtSearch1;
	JButton btnSearch1;
	protected ResultSet result1;


	public CuaSoCuaToi(String tieude) throws SQLException {
		super(tieude);
		addControls();
		ketnoidatabase();
		themtoanbothongtin();
		SuLySuKien();
	}
	
	private void themtoanbothongtin() throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from trunks";
		statement = conn.createStatement();
		ResultSet result=statement.executeQuery(sql);
		dtm.setRowCount(0);
        while(result.next()) {
        	Vector<Object> vec = new Vector<>();
            String id = result.getString(1);
            String ten = result.getString(2);
            String gioitinh = result.getString(3);
            float diemToan = result.getFloat(4);
            float diemVan = result.getFloat(5);
            float diemAnh = result.getFloat(6);
            people sv = new people(id, ten, gioitinh, diemToan, diemVan, diemAnh);
            vec.add(sv.getId());
            vec.add(sv.getTen());
            vec.add(sv.getGioiTinh());
            vec.add(sv.getDiemToan());
            vec.add(sv.getDiemVan());
            vec.add(sv.getDiemAnh());
            vec.add(sv.DiemTrungBinh());
            vec.add(sv.XepLoai());
            dtm.addRow(vec);
            
        }
		
	}

	private void ketnoidatabase() {
		// TODO Auto-generated method stub
	    try {
	        String strConn = "jdbc:mysql://localhost/Trunks";
	        Properties pro = new Properties();
	        pro.put("user", "root");
	        conn = DriverManager.getConnection(strConn, pro);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}

	public void addControls() {
	    Container con=getContentPane();
	    JPanel main=new JPanel();
	    main.setLayout(new FlowLayout());
	    JPanel pnLeft=new JPanel();
	    pnLeft.setPreferredSize(new Dimension(500,0));
	    JPanel pnRight=new JPanel();
	    pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));
	    JSplitPane sp =new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnLeft, pnRight);
	    
	
	    Border borderpnLeft=BorderFactory.createLineBorder(Color.BLUE);
	    TitledBorder borderTitlepnLeft=new TitledBorder(borderpnLeft, "Displays information of students");
	    pnLeft.setBorder(borderTitlepnLeft);
	    
	    sp.setPreferredSize(new Dimension(1000, 500));
	    main.add(sp);
	    con.add(main);
	    
	    dtm= new DefaultTableModel();
	    dtm.addColumn("ID");
	    dtm.addColumn("Name");
	    dtm.addColumn("Gender");
	    dtm.addColumn("Math Score");
	    dtm.addColumn("Literature Score");
	    dtm.addColumn("English Score");
	    dtm.addColumn("Average Score");
	    dtm.addColumn("Type");
	    tbJTable=new JTable(dtm);
	    sc=new JScrollPane(tbJTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    pnLeft.add(sc, BorderLayout.CENTER);
	    
	    JPanel pnRight1 = new JPanel();
	    Border borderRight1=BorderFactory.createLineBorder(Color.blue);
	    TitledBorder borderTitleRight1=new TitledBorder(borderRight1, "Enter Student Information");
	    pnRight1.setBorder(borderTitleRight1);
	    
	    JPanel ThongTin =new JPanel();
	    ThongTin.setLayout(new BoxLayout(ThongTin, BoxLayout.Y_AXIS));
	    JPanel pnID=new JPanel();
	    pnID.setLayout(new FlowLayout());
	    JLabel ID=new JLabel("Student's ID");
	    txtID=new JTextField(20);
	    ThongTin.add(pnID);
	    pnID.add(ID);
	    pnID.add(txtID);
	    ThongTin.add(pnID);
	    
	    
	    JPanel pnTen =new JPanel();
	    pnTen.setLayout(new FlowLayout());
	    JLabel Ten=new JLabel("Student's name");
	    txtTen=new JTextField(20);
	    ThongTin.add(pnTen);
	    pnTen.add(Ten);
	    pnTen.add(txtTen);
	    ThongTin.add(pnTen);
	    
	    JPanel pnGioiTinh =new JPanel();
	    pnGioiTinh.setLayout(new FlowLayout());
	    JLabel GioiTinh=new JLabel("Student gender");
	    radNam=new JRadioButton("Male");
	    radNu=new JRadioButton("Female");
	    btg = new ButtonGroup();
	    btg.add(radNam);
	    btg.add(radNu);
	    pnGioiTinh.add(GioiTinh);
	    pnGioiTinh.add(radNam);
	    pnGioiTinh.add(radNu);
	    ThongTin.add(pnGioiTinh);
	    
	    JPanel pnDiemToan =new JPanel();
	    pnDiemToan.setLayout(new FlowLayout());
	    JLabel DiemToan=new JLabel("Math score");
	    txtDiemToan=new JTextField(20);
	    ThongTin.add(DiemToan);
	    pnDiemToan.add(DiemToan);
	    pnDiemToan.add(txtDiemToan);
	    ThongTin.add(pnDiemToan);

	    
	    JPanel pnDiemVan =new JPanel();
	    pnDiemToan.setLayout(new FlowLayout());
	    JLabel DiemVan=new JLabel("Literature score");
	    txtDiemVan=new JTextField(20);
	    ThongTin.add(DiemVan);
	    pnDiemVan.add(DiemVan);
	    pnDiemVan.add(txtDiemVan);
	    ThongTin.add(pnDiemVan);
	    
	    JPanel pnDiemAnh =new JPanel();
	    pnDiemToan.setLayout(new FlowLayout());
	    JLabel DiemAnh=new JLabel("English score");
	    txtDiemAnh=new JTextField(20);
	    ThongTin.add(DiemAnh);
	    pnDiemAnh.add(DiemAnh);
	    pnDiemAnh.add(txtDiemAnh);
	    ThongTin.add(pnDiemAnh);
	   
	    pnRight1.add(ThongTin);
	    pnRight.add(pnRight1);
	    
	    JPanel pnRight2=new JPanel();
	    pnRight2.setLayout(new BoxLayout(pnRight2, BoxLayout.Y_AXIS));
	    Border borderRight2=BorderFactory.createLineBorder(Color.BLUE);
	    TitledBorder borderTitleRight2=new TitledBorder(borderRight2, "Menu function");
	    pnRight2.setBorder(borderTitleRight2);
	    JPanel ChucNang=new JPanel();
	    ChucNang.setLayout(new BoxLayout(ChucNang, BoxLayout.X_AXIS));
	    btnThem = new JButton("Insert");
	    btnSua = new JButton("Update");
	    btnXoa = new JButton("Delete");
	    btnXoaAll = new JButton("Clear");
	    btnSapXep = new JButton("Sort by Score");
	    btnFile = new JButton("File");

	    ChucNang.add(btnThem);
	    ChucNang.add(btnSua);
	    ChucNang.add(btnXoa);
	    ChucNang.add(btnXoaAll);
	    ChucNang.add(btnSapXep);
	    ChucNang.add(btnFile);
	    
	    JPanel pnSearch = new JPanel();
	    pnSearch.setLayout(new BoxLayout(pnSearch, BoxLayout.Y_AXIS));
	    cb=new JComboBox<String>();
	    cb.addItem("Math score");
	    cb.addItem("Literature score");
	    cb.addItem("English score");
	    pnSearch.add(cb);
	    txtSearch1 = new JTextField(20);
	    btnSearch1 = new JButton("Search");
	    pnSearch.add(txtSearch1);
	    pnSearch.add(btnSearch1);
	    
	    
	    
	    JLabel lblSearch = new JLabel("Find");
	    txtSearch = new JTextField(20);
	    btnSearch = new JButton("Search");
	    pnSearch.add(lblSearch);
	    pnSearch.add(txtSearch);
	    pnSearch.add(btnSearch);
	    pnRight2.add(pnSearch);
	    pnRight2.add(ChucNang);
	    pnRight.add(pnRight2);
	    
	    ID.setPreferredSize(DiemVan.getPreferredSize());
	    Ten.setPreferredSize(DiemVan.getPreferredSize());
	    DiemToan.setPreferredSize(DiemVan.getPreferredSize());
	    DiemVan.setPreferredSize(DiemVan.getPreferredSize());
	    DiemAnh.setPreferredSize(DiemVan.getPreferredSize());
	    GioiTinh.setPreferredSize(DiemVan.getPreferredSize());


	}
	
	public void SuLySuKien() {
		btnThem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		  
		            float toan = Float.parseFloat(txtDiemToan.getText());
		            float van = Float.parseFloat(txtDiemVan.getText());
		            float anh = Float.parseFloat(txtDiemAnh.getText());
		            String id = txtID.getText();
		            String ten = txtTen.getText();
		            String gioitinh = ((radNam.isSelected()) ? radNam.getText() : radNu.getText());

		            if (toan < 0 || van < 0 || anh < 0) {
		                throw new NegativeNumberException("NEGATIVE NUMBERS ARE NOT ALLOWED");
		            }

		            if (toan >= 0 || van >= 0 || anh >= 0) {
		                sinhvien sv = new people(id, ten, gioitinh, toan, van, anh);

		                String sql = "INSERT INTO trunks VALUES ('" +
		                        sv.getId() + "','" +
		                        sv.getTen() + "','" +
		                        sv.getGioiTinh() + "','" +
		                        sv.getDiemToan() + "','" +
		                        sv.getDiemVan() + "','" +
		                        sv.getDiemAnh() + "')";

		                try {
		                    statement = conn.createStatement();
		                    int cot = statement.executeUpdate(sql);
		                } catch (SQLException ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "There is already a student with this ID");
		                }

		                try {
		                    fouts = new FileOutputStream(outFile, true);
		                    douts = new DataOutputStream(fouts);

		                    douts.writeUTF(sv.getId());
		                    douts.writeUTF(sv.getTen());
		                    douts.writeUTF(sv.getGioiTinh());
		                    douts.writeFloat(sv.getDiemToan());
		                    douts.writeFloat(sv.getDiemVan());
		                    douts.writeFloat(sv.getDiemAnh());
		                    douts.close();
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                }

		                try {
		                    themtoanbothongtin();
		                } catch (SQLException ex) {
		                    ex.printStackTrace();
		                }
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Please enter a valid number");
		        } catch (NegativeNumberException ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage());
		        }
		    }
		});
                    
	       tbJTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) {
	                int chon = tbJTable.getSelectedRow();
	                if (chon >= 0) {
	                    String setid = (String) tbJTable.getValueAt(chon, 0);
	                    String setten = (String) tbJTable.getValueAt(chon, 1);
	                    String setgioitinh = (String) tbJTable.getValueAt(chon, 2);
	                    float setdiemtoan = (float) tbJTable.getValueAt(chon, 3);
	                    float setdiemvan = (float) tbJTable.getValueAt(chon, 4);
	                    float setdiemanh = (float) tbJTable.getValueAt(chon, 5);

	                    txtID.setText(setid);
	                    txtTen.setText(setten);
	                    radNam.setSelected(setgioitinh.equalsIgnoreCase("Male"));
	                    radNu.setSelected(setgioitinh.equalsIgnoreCase("Female"));
	                    txtDiemToan.setText(String.valueOf(setdiemtoan));
	                    txtDiemVan.setText(String.valueOf(setdiemvan));
	                    txtDiemAnh.setText(String.valueOf(setdiemanh));
	                    
	                    
	                }
	            }
	        });
	       btnSua.addActionListener(new ActionListener() {
	    	    @Override
	    	    public void actionPerformed(ActionEvent e) {
	    	        try {
	    	            int chon = tbJTable.getSelectedRow();
	    	            if (chon >= 0) {
	    	                String diemToan = txtDiemToan.getText();
	    	                String diemVan = txtDiemVan.getText();
	    	                String diemAnh = txtDiemAnh.getText();
	    	                float toan = Float.parseFloat(diemToan);
	    	                float van = Float.parseFloat(diemVan);
	    	                float anh = Float.parseFloat(diemAnh);

	    	                if (toan < 0 || van < 0 || anh < 0) {
	    	                    throw new NegativeNumberException("NEGATIVE NUMBERS ARE NOT ALLOWED");
	    	                }

	    	                String id = txtID.getText();
	    	                String ten = txtTen.getText();
	    	                String gioitinh = ((radNam.isSelected()) ? "Male" : "Female");

	    	                sinhvien sv1 = new people(id, ten, gioitinh, toan, van, anh);
	    	                String sql = "UPDATE trunks SET " +
	    	                        "Ten='" + sv1.getTen() + "', " +
	    	                        "GioiTinh='" + sv1.getGioiTinh() + "', " +
	    	                        "DiemToan='" + sv1.getDiemToan() + "', " +
	    	                        "DiemVan='" + sv1.getDiemVan() + "', " +
	    	                        "DiemAnh='" + sv1.getDiemAnh() + "' " +
	    	                        "WHERE ID='" + sv1.getId() + "'";
	    	                try {
	    	                    statement = conn.createStatement();
	    	                    int cot = statement.executeUpdate(sql);
	    	                    themtoanbothongtin();
	    	                } catch (SQLException e1) {
	    	                    e1.printStackTrace();
	    	                    // Handle SQLException here
	    	                }
	    	            } else {
	    	                JOptionPane.showMessageDialog(null, "Please select a row");
	    	            }
	    	        } catch (NumberFormatException ex) {
	    	            JOptionPane.showMessageDialog(null, "Please enter a valid number");
	    	        } catch (NegativeNumberException ex) {
	    	            JOptionPane.showMessageDialog(null, ex.getMessage());
	    	        }
	    	    }
	    	});

	       btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int chon=tbJTable.getSelectedRow();
				if(chon>=0) {
					String sql="DELETE from trunks where ID='"+txtID.getText()+"'";
    	            try {
    	                statement = conn.createStatement();
    	                int cot = statement.executeUpdate(sql);
    	                themtoanbothongtin();
    	            } catch (SQLException e1) {
    	                e1.printStackTrace();
    	            }
    	        } else {
    	            JOptionPane.showMessageDialog(null, "Please select a row to delete");
    	        }
				}
				
				
			
		});
	       
	       btnSearch.addActionListener(new ActionListener() {
	    	    @Override
	    	    public void actionPerformed(ActionEvent e) {
	    	        try {
	    	            if (txtSearch.getText() != "") {
	    	                dtm.setRowCount(0);
	    	                String sql = "SELECT * FROM trunks WHERE ID LIKE '%" + txtSearch.getText() + "%'";
	    	                statement = conn.createStatement();
	    	                ResultSet result1 = statement.executeQuery(sql);
	    	                dtm.setRowCount(0);
	    	                while (result1.next()) {
	    	                	Vector<Object> vec = new Vector<>();
	    	                    String id = result1.getString(1);
	    	                    String ten = result1.getString(2);
	    	                    String gioitinh = result1.getString(3);
	    	                    float diemToan = result1.getFloat(4);
	    	                    float diemVan = result1.getFloat(5);
	    	                    float diemAnh = result1.getFloat(6);
	    	                    sinhvien sv = new people(id, ten, gioitinh, diemToan, diemVan, diemAnh);
	    	                    vec.add(sv.getId());
	    	                    vec.add(sv.getTen());
	    	                    vec.add(sv.getGioiTinh());
	    	                    vec.add(sv.getDiemToan());
	    	                    vec.add(sv.getDiemVan());
	    	                    vec.add(sv.getDiemAnh());
	    	                    vec.add(sv.DiemTrungBinh());
	    	                    vec.add(sv.XepLoai());
	    	                    dtm.addRow(vec);
	    	                }
	    	            }
	    	            
	    	            else {
	    	                themtoanbothongtin();
	    	            }
	    	        } catch (SQLException ex) {
	    	            ex.printStackTrace();
	    	        }
	    	    }
	    	});
	       btnXoaAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int o = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete everything?", "Confirm deletion", JOptionPane.YES_NO_OPTION);
				if(o==JOptionPane.YES_OPTION) {
					
				String sql="DELETE from trunks";
				try {
					statement=conn.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					int x =statement.executeUpdate(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					themtoanbothongtin();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}


				
			}
		});
	       btnFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sum="";
				try {
				fins=new FileInputStream(outFile);
				dins=new DataInputStream(fins);
				
				for (int i=0; i<dins.available(); i++) {
	                String id = dins.readUTF();
	                String ten = dins.readUTF();
	                String gioiTinh = dins.readUTF();
	                float diemToan = dins.readFloat();
	                float diemVan = dins.readFloat();
	                float diemAnh = dins.readFloat();
	                
	                sinhvien sv=new people(id, ten, gioiTinh, diemToan, diemVan, diemAnh);
	                sum+=sv.toString();

				}
				 dins.close();
				}
				catch (Exception e1) {
					// TODO: handle exception
				}
				JOptionPane.showMessageDialog(null, sum);
				
			}
		});
	       btnSapXep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql="SELECT * FROM trunks ORDER BY (DiemToan+DiemVan+DiemAnh)";
				try {
				statement=conn.createStatement();
				ResultSet result=statement.executeQuery(sql);
				dtm.setRowCount(0);
				while(result.next()) {
					Vector<Object> vec=new Vector<Object>();
		            String id = result.getString(1);
		            String ten = result.getString(2);
		            String gioitinh = result.getString(3);
		            float diemToan = result.getFloat(4);
		            float diemVan = result.getFloat(5);
		            float diemAnh = result.getFloat(6);
		            sinhvien sv = new people(id, ten, gioitinh, diemToan, diemVan, diemAnh);
		            vec.add(sv.getId());
		            vec.add(sv.getTen());
		            vec.add(sv.getGioiTinh());
		            vec.add(sv.getDiemToan());
		            vec.add(sv.getDiemVan());
		            vec.add(sv.getDiemAnh());
		            vec.add(sv.DiemTrungBinh());
		            vec.add(sv.XepLoai());
		            dtm.addRow(vec);
					
					
				}
				}
				catch(Exception e1) {
					
				}
			}
				
				
			
		});
	       btnSearch1.addActionListener(new ActionListener() {
	    	    @Override
	    	    public void actionPerformed(ActionEvent e) {
	    	        try {
	    	            
	    	            String selectedSubject = (String) cb.getSelectedItem();
	    	            String searchValue = txtSearch1.getText();
	    	            float searchFloatValue = Float.parseFloat(searchValue);
	    	            float tolerance = 0.0001f;
	    	            String sqlQuery = "";
	    	            if (selectedSubject.equalsIgnoreCase("Math score")) {
	    	                sqlQuery = "SELECT * FROM trunks WHERE DiemToan >= ? - ? AND DiemToan <= ? + ?";
	    	            } else if (selectedSubject.equalsIgnoreCase("Literature score")) {
	    	                sqlQuery = "SELECT * FROM trunks WHERE DiemVan >= ? - ? AND DiemVan <= ? + ?";
	    	            } else {
	    	                sqlQuery = "SELECT * FROM trunks WHERE DiemAnh >= ? - ? AND DiemAnh <= ? + ?";
	    	            }

	    	            try (PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {
	    	                preparedStatement.setFloat(1, searchFloatValue);
	    	                preparedStatement.setFloat(2, tolerance);
	    	                preparedStatement.setFloat(3, searchFloatValue);
	    	                preparedStatement.setFloat(4, tolerance);

	    	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	    	                    dtm.setRowCount(0); 

	    	                    while (resultSet.next()) {
	    	                        Vector<Object> rowData = new Vector<>();
	    	                        String id = resultSet.getString(1);
	    	                        String ten = resultSet.getString(2);
	    	                        String gioitinh = resultSet.getString(3);
	    	                        float diemToan = resultSet.getFloat(4);
	    	                        float diemVan = resultSet.getFloat(5);
	    	                        float diemAnh = resultSet.getFloat(6);

	    	                        sinhvien sv = new people(id, ten, gioitinh, diemToan, diemVan, diemAnh);

	    	                        rowData.add(sv.getId());
	    	                        rowData.add(sv.getTen());
	    	                        rowData.add(sv.getGioiTinh());
	    	                        rowData.add(sv.getDiemToan());
	    	                        rowData.add(sv.getDiemVan());
	    	                        rowData.add(sv.getDiemAnh());
	    	                        rowData.add(sv.DiemTrungBinh());
	    	                        rowData.add(sv.XepLoai());

	    	                        dtm.addRow(rowData); 
	    	                    }
	    	                }
	    	            }
	    	        } catch (NumberFormatException ex) {
	    	            JOptionPane.showMessageDialog(null, "Please enter a valid number");
	    	        } catch (Exception ex) {
	    	            // Handle exceptions here
	    	            ex.printStackTrace();
	    	        }
	    	    }
	    	});

	}

		
	public void ShowWindow() {
        this.setSize(1050, 560);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
		
	}

}

